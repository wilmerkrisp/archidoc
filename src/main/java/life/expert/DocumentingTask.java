package life.expert;



//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/10
//
//--------------------------------------------------------------------------------









import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;










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
		
		String     message    = extension.getFile();
		String     recipient    = extension.getPackages();
		System.out.println(message+" >> "+recipient );
		}
		
	}

 

 