package FileSystem;

public class File {
	private String name;
	private String text="";
	public File(String name)
	{
		this.name=name;
	}
	
	public void setName(String s)
	{
		this.name=s;
	}
	
	public void setString(String s)
	{
		this.text=s;
	}
	public String getName()
	{
		return this.name;
	}
	public String getString()
	{
		return this.text;
	}
}
