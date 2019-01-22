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
import java.util.ArrayList;
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
	
	
	
	@TaskAction
	void documentingArchitecture()
		{
		//System.out.println( "v12" );
		
		Project project = getProject();
		
		DocumentingExtension extension = project.getExtensions().findByType( DocumentingExtension.class );
		if( extension == null )
			{
			extension = new DocumentingExtension();
			}
		
		
		
		ScanResult scanResult = ClassGraphHelper.build( project ,
		                                                extension ).scan();
		ClassInfoList class_list = scanResult.getAllClasses();
		try
			{
			String filename = extension.getFile();
			class_list.generateGraphVizDotFile( createOrRetrieveDiagramFile( filename ) );
			}
		catch( IOException exception )
			{
			throw new RuntimeException( "Some IO exception in ClassGraph: " ,
			                            exception );
			}
			
		}
		
	}

 

 