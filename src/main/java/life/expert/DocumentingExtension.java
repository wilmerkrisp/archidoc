package life.expert;



//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/11
//
//--------------------------------------------------------------------------------









public class DocumentingExtension
	{
	
	
	
	private String file;
	
	
	
	public String getFile()
		{
		return file;
		}
	
	
	
	public void setFile( String file )
		{
		this.file = file;
		}
	
	
	
	private String packages;
	
	
	
	public String getPackages()
		{
		return packages;
		}
	
	
	
	public void setPackages( String packages )
		{
		this.packages = packages;
		}
	
	
	
	public Boolean enableAllInfo = false;
	
	
	
	public Boolean getEnableAllInfo()
		{
		
		return enableAllInfo;
		}
	
	
	
	public void setEnableAllInfo( final Boolean fake )
		{
		
		
		this.enableAllInfo = true;
		}
	
	
	
	public Boolean enableClassInfo = false;
	
	
	
	public Boolean getEnableClassInfo()
		{
		return enableClassInfo;
		}
	
	
	
	public void setEnableClassInfo( final Boolean fake )
		{
		this.enableClassInfo = true;
		}
	
	
	
	public Boolean enableFieldInfo = false;
	
	
	
	public Boolean getEnableFieldInfo()
		{
		return enableFieldInfo;
		}
	
	
	
	public void setEnableFieldInfo( final Boolean fake )
		{
		this.enableFieldInfo = true;
		}
	
	
	
	public Boolean enableMethodInfo = false;
	
	
	
	public Boolean getEnableMethodInfo()
		{
		return enableMethodInfo;
		}
	
	
	
	public void setEnableMethodInfo( final Boolean fake )
		{
		this.enableMethodInfo = true;
		}
	
	
	
	public Boolean ignoreFieldVisibility = false;
	
	
	
	public Boolean getIgnoreFieldVisibility()
		{
		return ignoreFieldVisibility;
		}
	
	
	
	public void setIgnoreFieldVisibility( final Boolean fake )
		{
		this.ignoreFieldVisibility = true;
		}
	
	
	
	public Boolean ignoreMethodVisibility = false;
	
	
	
	public Boolean getIgnoreMethodVisibility()
		{
		return ignoreMethodVisibility;
		}
	
	
	
	public void setIgnoreMethodVisibility( final Boolean fake )
		{
		this.ignoreMethodVisibility = true;
		}
		
		
		
	}
