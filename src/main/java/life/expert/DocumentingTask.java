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
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;









public class DocumentingTask
	extends DefaultTask
	{
	
	
	
	@TaskAction
	void documentingArchitecture()
		{
		DocumentingExtension extension = getProject().getExtensions().findByType( DocumentingExtension.class );
		if( extension == null )
			{
			extension = new DocumentingExtension();
			}
		
		//		String     message    = extension.getFile();
		//		String     recipient    = extension.getPackages();
		//
		System.out.println( extension.getEnableAllInfo() + " + " + extension.getFile() + " + " + extension.getPackages() );
		
		
		
		String[] pkg = extension.getPackages().toArray( new String[1]);
		String filename=extension.getFile();
		
		System.out.println(pkg);
		
		//ClassGraph#ignoreClassVisibility(), ClassGraph#ignoreFieldVisibility() and/or ClassGraph#ignoreMethodVisibility()
		
		try( ScanResult scanResult =                // Assign scanResult in try-with-resources
			     new ClassGraph()                    // Create a new ClassGraph instance
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

 

 