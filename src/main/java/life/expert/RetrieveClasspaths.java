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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;









/**
 * The type Retrieve classpaths.
 */
public class RetrieveClasspaths
	{
	
	
	
	/**
	 * Instantiates a new Retrieve classpaths.
	 */
	public RetrieveClasspaths()
		{
		
		}
	
	
	
	/**
	 * for using inside streamApi
	 * hide exception and return optional
	 *
	 * @param file
	 * 	the file
	 *
	 * @return the optional
	 */
	public static final Optional< URL > fileToUrl( File file )
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
	
	
	
	/**
	 * get gradle  main source set
	 */
	private static Set< SourceSet > sourceSetMain( Project project )
		{
		
		
		
		//Project              project    = getProject();
		JavaPluginConvention convention = project.getConvention().findPlugin( JavaPluginConvention.class );
		Set< SourceSet >     sourceSets = convention.getSourceSets().stream().filter( sourceSet -> sourceSet.getName().equals( SourceSet.MAIN_SOURCE_SET_NAME ) ).collect( Collectors.toSet() );
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
	public static final Set< URL > classPathsMainSourceSet( Project project )
		{
		if( project == null )
			{
			return Collections.emptySet();
			}
		
		//output.getClassesDirs().getFiles().stream()
		//StreamSupport.stream( conf.spliterator() ,false )
		//StreamSupport.stream(  output.getClassesDirs().spliterator() ,false )
		
		return sourceSetMain( project ).stream().map( SourceSet::getOutput ).flatMap( output -> StreamSupport.stream( output.getClassesDirs().spliterator() ,
		                                                                                                              false ) ).map( RetrieveClasspaths::fileToUrl ).filter( Optional::isPresent ).map( Optional::get ).collect( toSet() );
		}
	
	
	
	/**
	 * get all jar dependencies
	 *
	 * @param project
	 * 	the project
	 *
	 * @return the set
	 */
	public static final Set< URL > classPathsDependenciesJar( Project project )
		{
		if( project == null )
			{
			return Collections.emptySet();
			}
		//Project       p    = getProject();
		Configuration conf = project.getConfigurations().getByName( "runtimeClasspath" );
		return StreamSupport.stream( conf.spliterator() ,
		                             false ).filter( ( f ) -> f.getName().endsWith( "jar" ) ).map( RetrieveClasspaths::fileToUrl ).filter( Optional::isPresent ).map( Optional::get ).collect( toSet() );
			
		}
	
	
	
	/**
	 * Retrieve classpaths from dependencies jar and main source set
	 *
	 * @param project
	 * 	the project
	 *
	 * @return the urls of all artifacts for classloader
	 */
	public static final Set< URL > retrieveClasspaths( Project project )
		{
		if( project == null )
			{
			return Collections.emptySet();
			}
		
		Set< URL > s = classPathsDependenciesJar( project );
		s.addAll( classPathsMainSourceSet( project ) );
		//System.out.println( l );
		
		return s;
		}
	
	
	
	/**
	 * Retrieve classpaths from dependencies jar and main source set
	 *
	 * @param project
	 * 	the project
	 *
	 * @return the url [ ] of all artifacts for classloader
	 */
	public static final URL[] retrieveClasspathsForAllProjects( Project project )
		{
		if( project == null )
			{
			return new URL[0];
			}
		
		//project.getAllprojects().forEach( System.out::println );
		
		Set< URL > s = project.getAllprojects().stream().flatMap( p -> retrieveClasspaths( p ).stream() ).collect( toSet() );
		s.forEach( System.out::println );
		
		return s.toArray( new URL[0] );
		}
		
		
		
	}
