package life.expert;



//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/22
//
//--------------------------------------------------------------------------------









import io.github.classgraph.ClassGraph;
import org.gradle.api.Project;

import java.util.Arrays;









/**
 * The type Class graph helper.
 */
public final class ClassGraphHelper
	{
	
	
	
	private ClassGraphHelper()
		{
		super();
		throw new UnsupportedOperationException( "Dont use this PRIVATE constructor.Please use constructor with parameters." );
		}
	
	
	
	/**
	 * Build class graph.
	 *
	 * @param project
	 * 	the project
	 * @param extension
	 * 	the extension
	 *
	 * @return the class graph
	 */
	public static ClassGraph build( Project project ,
	                                DocumentingExtension extension )
		{
		String[] selected_packages = extension.getPackages().toArray( new String[1] );
		Object[] java_classpaths   = extension.getMultiProject() ? (Object[]) RetrieveClasspaths.retrieveClasspathForAllProjects( project ) : (Object[]) RetrieveClasspaths.retrieveClasspathForMainProject( project );
		
		
		
		ClassGraph cg = new ClassGraph()                    // Create a new ClassGraph instance
		                                                    .overrideClasspath( java_classpaths ) //get custom classpath
		                                                    .whitelistPackages( selected_packages );  // Scan com.xyz and subpackages
		
		if( extension.getEnableAllInfo() )
			{
			cg = cg.enableAllInfo();
			}
		
		if( extension.getEnableClassInfo() )
			{
			cg = cg.enableClassInfo();
			}
		
		if( extension.getEnableFieldInfo() )
			{
			cg = cg.enableFieldInfo();
			}
		
		if( extension.getEnableMethodInfo() )
			{
			cg = cg.enableMethodInfo();
			}
		
		if( extension.getIgnoreFieldVisibility() )
			{
			cg = cg.ignoreFieldVisibility();
			}
		
		if( extension.getIgnoreMethodVisibility() )
			{
			cg = cg.ignoreMethodVisibility();
			}
		
		if( extension.getVerbose() )
			{
			cg = cg.verbose();
			Arrays.stream( java_classpaths ).forEach( System.out::println );
			//System.out.println( java_classpaths );
			}
		
		
		
		return cg;
		}
		
		
	}
