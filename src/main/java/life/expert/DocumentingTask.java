package life.expert;



//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/10
//
//--------------------------------------------------------------------------------









import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.*;









/**
 * The type Documenting task.
 */
public class DocumentingTask
	extends DefaultTask
	{
	
	
	
	private void generateArchiOnDefaultFile( ClassInfoList classList )
		{
		File file;
		try
			{
			file = new File( "architecture.dot" );
			file.getParentFile().mkdirs();
			file.createNewFile();
			classList.generateGraphVizDotFile( file );
			}
		catch( IOException | NullPointerException exception )
			{
			throw new RuntimeException( "Please set correct path for file with filename. " ,
			                            exception );
				
			}
		}
	
	
	
	/**
	 * Creates a File if the file does not exist, or returns a
	 * reference to the File if it already exists.
	 */
	private File createOrRetrieve( final String target )
	throws IOException
		{
		
		Path path   = Paths.get( target );
		Path       parent = path.getParent();
		
		
		if( parent == null )
			{
			Path build_dir=getProject().getBuildDir().toPath();
			parent=build_dir.resolve( "architecture" );
			path=parent.resolve(path);
			System.out.println( "!Parent2: "+path.toAbsolutePath());
			}
		
		
		if( Files.notExists( path ) )
			{
			
			
			
			//LOG.info( "Target file \"" + target + "\" will be created." );
			System.out.println( "Target file " + path.toAbsolutePath() + " will be created." + parent );
			
			
			Files.createDirectories( parent );
			
			System.out.println( "!then file: " );
			if( Files.notExists( path ) )
				Files.createFile( path ).toFile();
			}
		
		System.out.println( "Target file \"" + path.toAbsolutePath() + "\" will be retrieved." );
		return path.toFile();
		}
	
	
	
	@TaskAction
	void documentingArchitecture()
		{
		System.out.println( "v9" );
		
		DocumentingExtension extension = getProject().getExtensions().findByType( DocumentingExtension.class );
		if( extension == null )
			{
			extension = new DocumentingExtension();
			}
		
		//		String     message    = extension.getFile();
		//		String     recipient    = extension.getPackages();
		//
		System.out.println( extension.getEnableAllInfo() + " + " + extension.getFile() + " + " + extension.getPackages() );
		
		//getProject().
		
		String[] pkg      = extension.getPackages().toArray( new String[1] );
		String   filename = extension.getFile();
		System.out.println( pkg );
		
		
		//
		
		//ClassGraph#ignoreClassVisibility(), ClassGraph#ignoreFieldVisibility() and/or ClassGraph#ignoreMethodVisibility()
		
		try( ScanResult scanResult =                // Assign scanResult in try-with-resources
			     new ClassGraph()                    // Create a new ClassGraph instance
			                                         .overrideClasspath( (Object[]) RetrieveClasspaths.retrieveClasspathsForAllProjects( getProject() ) ).verbose()                      // If you want to enable logging to stderr
			                                         .enableAllInfo()                // Scan classes, methods, fields, annotations
			                                         .whitelistPackages( pkg )   // Scan com.xyz and subpackages
			                                         .scan() )
			{                      // Perform the scan and return a ScanResult
			// Use the ScanResult within the try block, e.g.
			//ClassInfo     widgetClassInfo = scanResult.getClassInfo( "life.expert" );
			ClassInfoList class_list = scanResult.getAllClasses();
			
			
			
			File file;
			
			
			try
				{
				file = createOrRetrieve( filename );
				System.out.println( "!!file created: " );
				
				
				class_list.generateGraphVizDotFile( file );
				}
			catch( IOException | NullPointerException exception )
				{
				throw new RuntimeException( "Please set correct path for file with filename. For example file  \"$buildDir/architecture/classdiagram.dot\" " ,
				                            exception );
				}
				
				
				
			}
			
			
			
		}
		
	}

 

 