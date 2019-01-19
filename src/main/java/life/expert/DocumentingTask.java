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
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.*;









public class DocumentingTask
	extends DefaultTask
	{
	
	
	
	public Optional< URL > fileToUrl( File file )
		{
		try
			{
			return Optional.ofNullable( file.toURI().toURL() );
			}
		catch( MalformedURLException e )
			{
			return Optional.empty();
			}
		}
	
	
	
	private List< SourceSet > sourceSetMain()
		{
		Project              project    = getProject();
		JavaPluginConvention convention = project.getConvention().findPlugin( JavaPluginConvention.class );
		List< SourceSet >    sourceSets = convention.getSourceSets().stream().filter( sourceSet -> sourceSet.getName().equals( SourceSet.MAIN_SOURCE_SET_NAME ) ).collect( Collectors.toList() );
		return sourceSets;
		}
	
	
	
	public Set< URL > classPathsMainSourceSet()
		{
		//output.getClassesDirs().getFiles().stream()
		//StreamSupport.stream( conf.spliterator() ,false )
		//StreamSupport.stream(  output.getClassesDirs().spliterator() ,false )
		
		return sourceSetMain().stream().map( SourceSet::getOutput ).flatMap( output -> StreamSupport.stream( output.getClassesDirs().spliterator() ,
		                                                                                                     false ) ).map( this::fileToUrl ).filter( Optional::isPresent ).map( Optional::get ).collect( toSet() );
		}
	
	
	
	public List< URL > classPathsDependenciesJar()
		{
		Project       p    = getProject();
		Configuration conf = p.getConfigurations().getByName( "runtimeClasspath" );
		return StreamSupport.stream( conf.spliterator() ,
		                             false ).filter( ( f ) -> f.getName().endsWith( "jar" ) ).map( this::fileToUrl ).filter( Optional::isPresent ).map( Optional::get ).collect( toList() );
			
		}
	
	
	
	@TaskAction
	void documentingArchitecture()
		{
		System.out.println( "v4" );
		
		DocumentingExtension extension = getProject().getExtensions().findByType( DocumentingExtension.class );
		if( extension == null )
			{
			extension = new DocumentingExtension();
			}
		
		//		String     message    = extension.getFile();
		//		String     recipient    = extension.getPackages();
		//
		System.out.println( extension.getEnableAllInfo() + " + " + extension.getFile() + " + " + extension.getPackages() );
		
		
		
		String[] pkg      = extension.getPackages().toArray( new String[1] );
		String   filename = extension.getFile();
		System.out.println( pkg );
		
		
		
		//sourceSetMain.main.output.classesDirs.each { println it.toURI().toURL() }
		
		
		List<URL> l=classPathsDependenciesJar();
		l.addAll( classPathsMainSourceSet() );
		System.out.println( l );
		
		URL[] urls = l.toArray(new URL[0]);
		
		
		//ClassGraph#ignoreClassVisibility(), ClassGraph#ignoreFieldVisibility() and/or ClassGraph#ignoreMethodVisibility()
		
		try( ScanResult scanResult =                // Assign scanResult in try-with-resources
			     new ClassGraph()                    // Create a new ClassGraph instance
			                                         .overrideClasspath( urls )
			                                         .verbose()                      // If you want to enable logging to stderr
			                                         .enableAllInfo()                // Scan classes, methods, fields, annotations
			                                         .whitelistPackages( pkg )   // Scan com.xyz and subpackages
			                                         .scan() )
			{                      // Perform the scan and return a ScanResult
			// Use the ScanResult within the try block, e.g.
			//ClassInfo     widgetClassInfo = scanResult.getClassInfo( "life.expert" );
			ClassInfoList class_list = scanResult.getAllClasses();
			
			
			
			File file = new File( filename );
			file.getParentFile().mkdirs();
			//scanResult.getAllClasses().generateClassGraphDotFile(File file)
			
			//System.out.println( "!!!!!!!!!!!!!!!" + class_list );
			try
				{
				file.createNewFile();
				class_list.generateGraphVizDotFile( file );
				}
			catch( IOException exception )
				{
				throw new RuntimeException( "Please set correct path for file with filename. " ,
				                            exception );
					
				}
			
			
			
			// ...
			}
			
			
			
		}
		
	}

 

 