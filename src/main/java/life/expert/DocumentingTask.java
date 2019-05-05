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
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static life.expert.FileHelper.*;









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
		Path build_dir = getProject().getBuildDir()
		                             .toPath();
		Path parent = build_dir.resolve( DEFAULT_DIAGRAM_DIRECTORY_NAME_ );
		
		return createOrRetrieveFile( fileName ,
		                             parent );
		}
	
	
	
	@TaskAction
	void documentingArchitecture()
		{
		//System.out.println( "v12" );
		
		Project project = getProject();
		
		DocumentingExtension extension = project.getExtensions()
		                                        .findByType( DocumentingExtension.class );
		if( extension == null )
			{
			extension = new DocumentingExtension();
			}
		
		String file_name = extension.getFile();
		File   dot_file  = createOrRetrieveDiagramFile( file_name );
		
		ScanResult scanResult = ClassGraphHelper.build( project ,
		                                                extension )
		                                        .scan();
		ClassInfoList class_list = scanResult.getAllClasses();
		
		if( extension.getEnableInterClassDependencies() )
			{
			//			ioWrapper( () ->
			//			           {
			//			           try( final PrintWriter writer = new PrintWriter( dot_file ) )
			//				           {
			//				           writer.print( class_list.generateGraphVizDotFileFromClassDependencies() );
			//				           }
			//			           } );
			ioWrapper( writerWrapper( dot_file ,
			                          class_list::generateGraphVizDotFileFromClassDependencies ) );
			}
		else
			{
			ioWrapper( () -> class_list.generateGraphVizDotFile( dot_file ) );
			}
			
			
			
		}
		
	}

 

 