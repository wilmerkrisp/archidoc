package life.expert;



//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/11
//
//--------------------------------------------------------------------------------









import java.util.ArrayList;
import java.util.List;









/**
 * The type Documenting extension.
 */
public class DocumentingExtension
	{
	
	
	
	public static final String DEFAULT_FILENAME_ = "classdiagram.dot";
	
	
	
	private String file = DEFAULT_FILENAME_;
	
	
	
	/**
	 * Gets file.
	 *
	 * @return the file
	 */
	public String getFile()
		{
		return file;
		}
	
	
	
	/**
	 * Sets file.
	 *
	 * @param file
	 * 	the file
	 */
	public void setFile( String file )
		{
		this.file = file;
		}
	
	
	
	private ArrayList<String> packages = new ArrayList<String>();
	
	
	
	/**
	 * Gets packages.
	 *
	 * @return the packages
	 */
	public List<String> getPackages()
		{
		return packages;
		}
	
	
	
	/**
	 * Sets packages.
	 *
	 * @param packages
	 * 	the packages
	 */
	public void setPackages( List<String> packages )
		{
		this.packages.clear();
		this.packages.addAll( packages );
		}
	
	
	
	/**
	 * The Enable all info.
	 */
	public Boolean enableAllInfo = false;
	
	
	
	/**
	 * Gets enable all info.
	 *
	 * @return the enable all info
	 */
	public Boolean getEnableAllInfo()
		{
		
		return enableAllInfo;
		}
	
	
	
	/**
	 * Sets enable all info.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setEnableAllInfo( final Boolean fake )
		{
		
		
		this.enableAllInfo = true;
		}
	
	
	
	/**
	 * The Enable class info.
	 */
	public Boolean enableClassInfo = false;
	
	
	
	/**
	 * Gets enable class info.
	 *
	 * @return the enable class info
	 */
	public Boolean getEnableClassInfo()
		{
		return enableClassInfo;
		}
	
	
	
	/**
	 * Sets enable class info.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setEnableClassInfo( final Boolean fake )
		{
		this.enableClassInfo = true;
		}
	
	
	
	/**
	 * The Enable field info.
	 */
	public Boolean enableFieldInfo = false;
	
	
	
	/**
	 * Gets enable field info.
	 *
	 * @return the enable field info
	 */
	public Boolean getEnableFieldInfo()
		{
		return enableFieldInfo;
		}
	
	
	
	/**
	 * Sets enable field info.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setEnableFieldInfo( final Boolean fake )
		{
		this.enableFieldInfo = true;
		}
	
	
	
	/**
	 * The Enable method info.
	 */
	public Boolean enableMethodInfo = false;
	
	
	
	/**
	 * Gets enable method info.
	 *
	 * @return the enable method info
	 */
	public Boolean getEnableMethodInfo()
		{
		return enableMethodInfo;
		}
	
	
	
	/**
	 * Sets enable method info.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setEnableMethodInfo( final Boolean fake )
		{
		this.enableMethodInfo = true;
		}
	
	
	
	/**
	 * The Ignore field visibility.
	 */
	public Boolean ignoreFieldVisibility = false;
	
	
	
	/**
	 * Gets ignore field visibility.
	 *
	 * @return the ignore field visibility
	 */
	public Boolean getIgnoreFieldVisibility()
		{
		return ignoreFieldVisibility;
		}
	
	
	
	/**
	 * Sets ignore field visibility.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setIgnoreFieldVisibility( final Boolean fake )
		{
		this.ignoreFieldVisibility = true;
		}
	
	
	
	/**
	 * The Ignore method visibility.
	 */
	public Boolean ignoreMethodVisibility = false;
	
	
	
	/**
	 * Gets ignore method visibility.
	 *
	 * @return the ignore method visibility
	 */
	public Boolean getIgnoreMethodVisibility()
		{
		return ignoreMethodVisibility;
		}
	
	
	
	/**
	 * Sets ignore method visibility.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setIgnoreMethodVisibility( final Boolean fake )
		{
		this.ignoreMethodVisibility = true;
		}
	
	
	
	private Boolean verbose = false;
	
	
	
	/**
	 * Gets verbose.
	 *
	 * @return the verbose
	 */
	public Boolean getVerbose()
		{
		return verbose;
		}
	
	
	
	/**
	 * Sets verbose.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setVerbose( final Boolean fake )
		{
		this.verbose = true;
		}
	
	
	
	private Boolean multiProject = false;
	
	
	
	/**
	 * Gets verbose.
	 *
	 * @return the verbose
	 */
	public Boolean getMultiProject()
		{
		return multiProject;
		}
	
	
	
	/**
	 * Sets verbose.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setMultiProject( final Boolean fake )
		{
		this.multiProject = true;
		}
	
	
	
	private Boolean enableAnnotationInfo = false;
	
	
	
	/**
	 * Gets verbose.
	 *
	 * @return the verbose
	 */
	public Boolean getEnableAnnotationInfo()
		{
		return enableAnnotationInfo;
		}
	
	
	
	/**
	 * Sets verbose.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setEnableAnnotationInfo( final Boolean fake )
		{
		this.enableAnnotationInfo = true;
		}
	
	
	
	private Boolean ignoreClassVisibility = false;
	
	
	
	/**
	 * Gets verbose.
	 *
	 * @return the verbose
	 */
	public Boolean getIgnoreClassVisibility()
		{
		return ignoreClassVisibility;
		}
	
	
	
	/**
	 * Sets verbose.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setIgnoreClassVisibility( final Boolean fake )
		{
		this.ignoreClassVisibility = true;
		}
	
	
	
	private Boolean enableInterClassDependencies = false;
	
	
	
	/**
	 * Gets verbose.
	 *
	 * @return the verbose
	 */
	public Boolean getEnableInterClassDependencies()
		{
		return enableInterClassDependencies;
		}
	
	
	
	/**
	 * Sets verbose.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setEnableInterClassDependencies( final Boolean fake )
		{
		this.enableInterClassDependencies = true;
		}
	
	
	
	private Boolean enableExternalClasses = false;
	
	
	
	/**
	 * Gets verbose.
	 *
	 * @return the verbose
	 */
	public Boolean getEnableExternalClasses()
		{
		return enableExternalClasses;
		}
	
	
	
	/**
	 * Sets verbose.
	 *
	 * @param fake
	 * 	the fake
	 */
	public void setEnableExternalClasses( final Boolean fake )
		{
		this.enableExternalClasses = true;
		}
		
		
		
	}
