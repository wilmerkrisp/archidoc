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
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;









/**
 * The type Documenting task.
 */
public class DocumentingTask
	extends DefaultTask
	{
	
	
	
	private static final String DEFAULT_DIAGRAM_DIRECTORY_NAME_ = "architecture";
	
	
	
	/*
	 * Creates a File if the file does not exist, or returns a
	 * reference to the File if it already exists.
	 *
	 * @param fileName
	 * 	the file name
	 *
	 * @return the file
	 */
	private File createOrRetrieveDiagramFile( final String fileName )
		
		{
		Path build_dir = getProject().getBuildDir().toPath();
		Path parent    = build_dir.resolve( DEFAULT_DIAGRAM_DIRECTORY_NAME_ );
		
		return FileHelper.createOrRetrieveFile( fileName ,
		                                        parent );
		}
	
	
	
	private ClassGraph build(DocumentingExtension extension)
		{
		String[] selected_packages      = extension.getPackages().toArray( new String[1] );
		Object[] java_classpaths = extension.getMultiProject() ? (Object[]) RetrieveClasspaths.retrieveClasspathForAllProjects( getProject() ) : (Object[]) RetrieveClasspaths.retrieveClasspathForMainProject( getProject() );
		
		
		ClassGraph cg=new ClassGraph()                    // Create a new ClassGraph instance
		                                                  .overrideClasspath( java_classpaths ) //get custom classpath
		                                                  .verbose()                      // If you want to enable logging to stderr
		                                                  .enableAllInfo()                // Scan classes, methods, fields, annotations
		                                                  .whitelistPackages( selected_packages ) ;  // Scan com.xyz and subpackages
		return null;
		}
	
	
	
	private void scanProgram(DocumentingExtension extension, ConsumerIO< ClassInfoList > processScanResult )
		{
		ScanResult scanResult = build(extension).scan();
		
		
		
		// Perform the scan and return a ScanResult
		ClassInfoList class_list = scanResult.getAllClasses();
		try
			{
			processScanResult.accept( class_list );
			}
		catch( IOException exception )
			{
			throw new RuntimeException( "Some IO exception in ClassGraph: " ,
			                            exception );
			}
			
			
			
		}
	
	
	
	@TaskAction
	void documentingArchitecture()
		{
		System.out.println( "v10" );
		
		
		
		DocumentingExtension extension = getProject().getExtensions().findByType( DocumentingExtension.class );
		if( extension == null )
			{
			extension = new DocumentingExtension();
			}
		
		
		String   filename      = extension.getFile();
		
		scanProgram( extension ,
		             forSelectedClasses -> forSelectedClasses.generateGraphVizDotFile( createOrRetrieveDiagramFile( filename ) ) );
		
		//System.out.println( pkg );
		
		
		//class_list.generateGraphVizDotFile( createOrRetrieveDiagramFile( filename ) )
		
		//
		
		//ClassGraph#ignoreClassVisibility(), ClassGraph#ignoreFieldVisibility() and/or ClassGraph#ignoreMethodVisibility()
		
		
		
		}
		
	}

 

 