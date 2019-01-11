package life.expert;









import org.gradle.api.Plugin;
import org.gradle.api.Project;



//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/10
//
//--------------------------------------------------------------------------------









/**
 * The type Documenting software architecture.
 */
public class DocumentingSoftwareArchitecture
	implements Plugin< Project >
	{
	
	
	
	@Override
	public void apply( Project target )
		{
		target.getExtensions().create("archidoc", DocumentingExtension.class);
		target.getTasks().create("archidoc", DocumentingTask.class);
		
		}
	}
