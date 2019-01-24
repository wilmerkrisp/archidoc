package life.expert;









import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.PublishArtifactSet;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.SourceSet;
import org.gradle.internal.impldep.org.apache.ivy.util.url.URLHandlerDispatcher;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;



//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/10
//
//--------------------------------------------------------------------------------









/**
 * The type Documenting software architecture.
 *
 * Gradle User Manual Version 5.1 "Iterating over dependencies assigned to a configuration"
 *
 *
 * task example0 {
 * dependsOn configurations.runtimeClasspath, build
 * doLast {
 *
 * configurations.runtimeClasspath.findAll { it.name.endsWith('jar') }.collect { println it.toURI().toURL() }
 * sourceSets.main.output.classesDirs.each { println it.toURI().toURL() }
 * }
 * }
 */
public class DocumentingSoftwareArchitecture
	implements Plugin< Project >
	{
	
	
	
	public static final String TASK_NAME   = "archidoc";
	
	
	
	public static final String DESCRIPTION = "Generate diagram of classes (graphviz .dot file) of your projects.";
	
	
	
	public static final String GROUP       = "documentation";
	
	
	
	@Override
	public void apply( Project target )
		{
		target.getExtensions().create( TASK_NAME ,
		                               DocumentingExtension.class );
		DocumentingTask t = target.getTasks().create( TASK_NAME ,
		                                              DocumentingTask.class );
		t.setGroup( GROUP );
		t.setDescription( DESCRIPTION );
		//t.setGroup( "architecture" );
		//t.setDescription( "Generates class diagramm for the main source code." );
		//t.dependsOn( target.getTasks().getByPath( ":build" ) );
		}
	}
