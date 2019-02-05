package life.expert;



//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/21
//
//--------------------------------------------------------------------------------









import com.google.common.flogger.FluentLogger;
import org.gradle.api.Project;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Supplier;









/**
 * interface static
 * !CHANGE_ME_DESCRIPTION!
 *
 * - сервисные методы делать статик методами на классе с private конструктором а не на интерфейсе тк методы обработки могут содержать состояние и кеши
 * - интрфейсы использовать только для задания типа
 *
 *
 * <pre>{@code
 *
 *
 *
 *
 * example 2
 *
 *               FileHelper.fs_service();
 *               v_1=FileHelper.s_const;
 *
 *
 *
 *
 * }*****</pre>
 */
public final class FileHelper
	{
	
	
	
	private static final FluentLogger logger_ = FluentLogger.forEnclosingClass();
	
	
	
	private FileHelper()
		{
		super();
		
		throw new UnsupportedOperationException( "Dont use this PRIVATE constructor.Please use constructor with parameters." );
		}
	
	
	
	/**
	 * for using inside streamApi
	 * hide exception and return optional
	 *
	 * @param file
	 * 	the file
	 *
	 * @return the optional
	 */
	public static Optional< URL > fileToUrl( File file )
		{
		try
			{
			return Optional.ofNullable( file.toURI()
			                                .toURL() );
			}
		catch( MalformedURLException e )
			{
			return Optional.empty();
			}
		}
	
	
	
	/**
	 * Create or retrieve file file.
	 *
	 * @param fileName
	 * 	the file name
	 * @param defaultDir
	 * 	the default dir
	 *
	 * @return the file
	 */
	public static File createOrRetrieveFile( final String fileName ,
	                                         Path defaultDir )
		{
		Path path   = Paths.get( fileName );
		Path parent = path.getParent();
		
		try
			{
			if( parent == null )
				{
				//Path build_dir = project.getBuildDir().toPath();
				//parent = defaultDir.resolve( "architecture" );
				path = parent.resolve( path );
				
				logger_.atInfo()
				       .log( "Build directory used: %s" , path.toAbsolutePath() );
				}
			if( Files.notExists( path ) )
				{
				Files.createDirectories( parent );
				Files.createFile( path )
				     .toFile();
				
				logger_.atInfo()
				       .log( "Target file created: %s" , path.toAbsolutePath() );
				}
			else
				{
				
				logger_.atInfo()
				       .log( "Target file retrieved: %s" , path.toAbsolutePath() );
				}
			}
		catch( IOException exception )
			{
			throw new RuntimeException( "Please set correct path for file with filename. For example file  \"$buildDir/architecture/classdiagram.dot\" " , exception );
			}
		return path.toFile();
		}
	
	
	
	/**
	 * Io wrapper.
	 *
	 * @param classGraphGenerate
	 * 	the class graph generate
	 */
	public static void ioWrapper( RunnableIO classGraphGenerate )
		{
		try
			{
			classGraphGenerate.run();
			}
		catch( IOException exception )
			{
			throw new RuntimeException( "Some IO exception in ClassGraph: " , exception );
			}
		}
	
	
	
	/**
	 * Writer wrapper runnable io.
	 *
	 * @param file
	 * 	the file
	 * @param classGraphGenerate
	 * 	the class graph generate
	 *
	 * @return the runnable io
	 */
	public static RunnableIO writerWrapper( File file ,
	                                        Supplier< String > classGraphGenerate )
		{
		return () ->
		{
		try( final PrintWriter writer = new PrintWriter( file ) )
			{
			writer.print( classGraphGenerate.get() );
			}
		};
		}
		
	}
