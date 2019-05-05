package life.expert;



//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/19
//
//--------------------------------------------------------------------------------









import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;

import java.net.URL;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toSet;









/**
 * The type Retrieve classpaths.
 */
public final class RetrieveClasspaths
	{
	
	
	
	private static String JAR_FILE_EXTENSION = "jar";
	
	
	
	private static final String RUNTIME_CLASSPATH_CONFIGURATION = "runtimeClasspath";
	
	
	
	/**
	 * Instantiates a new Retrieve classpaths.
	 */
	private RetrieveClasspaths()
		{
		
		throw new UnsupportedOperationException( "Please use constructor with parameters." );
		
		}
	
	
	
	/**
	 * get gradle  main source set
	 */
	private static Set<SourceSet> sourceSetMain( Project project )
		{
		JavaPluginConvention convention = project.getConvention()
		                                         .findPlugin( JavaPluginConvention.class );
		Set<SourceSet>       sourceSets = convention.getSourceSets()
		                                            .stream()
		                                            .filter( sourceSet -> sourceSet.getName()
		                                                                           .equals( SourceSet.MAIN_SOURCE_SET_NAME ) )
		                                            .collect( Collectors.toSet() );
		return sourceSets;
		}
	
	
	
	/**
	 * get directory urls of output (build directory contain .class and subdirectories)
	 * uses main source set
	 *
	 * @param project
	 * 	the project
	 *
	 * @return the set or empty value if input argument empty
	 */
	public static Set<URL> classPathsMainSourceSet( Project project )
		{
		if( project == null )
			{
			return Collections.emptySet();
			}
		
		return sourceSetMain( project ).stream()
		                               .map( SourceSet::getOutput )
		                               .flatMap( output -> StreamSupport.stream( output.getClassesDirs()
		                                                                               .spliterator() ,
		                                                                         false ) )
		                               .map( FileHelper::fileToUrl )
		                               .filter( Optional::isPresent )
		                               .map( Optional::get )
		                               .collect( toSet() );
		}
	
	
	
	/**
	 * get all jar dependencies
	 *
	 * @param project
	 * 	the project
	 *
	 * @return the set
	 */
	public static Set<URL> classPathsDependenciesJar( Project project )
		{
		if( project == null )
			{
			return Collections.emptySet();
			}
		
		Configuration conf = project.getConfigurations()
		                            .getByName( RUNTIME_CLASSPATH_CONFIGURATION );
		return StreamSupport.stream( conf.spliterator() ,
		                             false )
		                    .filter( ( f ) -> f.getName()
		                                       .endsWith( JAR_FILE_EXTENSION ) )
		                    .map( FileHelper::fileToUrl )
		                    .filter( Optional::isPresent )
		                    .map( Optional::get )
		                    .collect( toSet() );
			
		}
	
	
	
	/**
	 * Retrieve classpaths from dependencies jar and main source set
	 *
	 * @param project
	 * 	the project
	 *
	 * @return the urls of all artifacts for classloader
	 */
	public static Set<URL> retrieveClasspath( Project project )
		{
		if( project == null )
			{
			return Collections.emptySet();
			}
		
		Set<URL> s = classPathsDependenciesJar( project );
		s.addAll( classPathsMainSourceSet( project ) );
		
		return s;
		}
	
	
	
	/**
	 * For all projects
	 * Retrieve classpaths from dependencies jar and main source set
	 *
	 * @param project
	 * 	root project
	 *
	 * @return the url [ ] of all artifacts for classloader
	 */
	public static URL[] retrieveClasspathForAllProjects( Project project )
		{
		if( project == null )
			{
			return new URL[0];
			}
		
		Set<URL> s = project.getAllprojects()
		                    .stream()
		                    .flatMap( p -> retrieveClasspath( p ).stream() )
		                    .collect( toSet() );
		
		return s.toArray( new URL[0] );
		}
	
	
	
	/**
	 * For main project
	 * Retrieve classpaths from dependencies jar and main source set
	 *
	 * @param project
	 * 	root project
	 *
	 * @return the url [ ] of all artifacts for classloader
	 */
	public static URL[] retrieveClasspathForMainProject( Project project )
		{
		if( project == null )
			{
			return new URL[0];
			}
		
		Set<URL> s = retrieveClasspath( project );
		
		return s.toArray( new URL[0] );
		}
		
		
	}
