package life.expert;









import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.gradle.testkit.runner.TaskOutcome.*;









public class DocumentingSoftwareArchitectureTest
	{
	
	
	
	@Rule public final TemporaryFolder testProjectDir = new TemporaryFolder();
	
	
	
	private File buildFile;
	
	
	
	@Before
	public void setup()
	throws IOException
		{
		buildFile = testProjectDir.newFile( "build.gradle" );
		}
	
	
	
	@Test
	public void testMyTask()
	throws IOException
		{
		
		String buildFileContent = "buildscript {\n" + "    repositories {  maven {\n" + "      url \"https://plugins.gradle.org/m2/\"\n" + "    }\n" + "    }\n" + "    dependencies {\n" + "        classpath 'gradle.plugin.life.expert:archidoc:1.0.7'\n" + "    }\n" + "}\n" + "apply plugin : 'life.expert.archidoc'\n apply plugin: 'java' ";
		//String buildFileContent = "plugins { id 'life.expert.archidoc' version '1.0.1' }";
		System.out.println( buildFileContent );
		
		writeFile( buildFile ,
		           buildFileContent );
		
		//BuildResult result = GradleRunner.create().withProjectDir( testProjectDir.getRoot() ).withArguments( "archidoc" ).build();
		
		//=assertTrue( result.getOutput().contains( "null >> null" ) );
		//System.out.println( result.getOutput() );
		//assertEquals( SUCCESS , result.task( ":archidoc" ).getOutcome() );
		}
	
	
	
	private void writeFile( File destination ,
	                        String content )
	throws IOException
		{
		BufferedWriter output = null;
		try
			{
			output = new BufferedWriter( new FileWriter( destination ) );
			output.write( content );
			}
		finally
			{
			if( output != null )
				{
				output.close();
				}
			}
		}
	}