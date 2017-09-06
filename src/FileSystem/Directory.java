package FileSystem;
import java.util.*;

public class Directory {
	private String name;
	private LinkedList<Directory> directories=new LinkedList<Directory>();
	private LinkedList<File> files=new LinkedList<File>();
	private int dirLen=0;
	private int fileLen=0;
	public Directory(String name)
	{
		this.name=name;
	}
	public void getInput()
	{
		//System.out.println(name);
		for(;;)
		{
			Scanner scan=new Scanner(System.in);
			String n=scan.nextLine();
			int nLen=n.length();
			if(nLen>6&&n.substring(0,6).equals("go to "))goTo(n.substring(6,nLen));
			else if(nLen>5&&n.substring(0,5).equals("read "))read(n.substring(5,nLen).trim());
			else if(nLen>15&&n.substring(0,15).equals("open -readonly "))openFileRo(n.substring(15,nLen).trim());
			else if(nLen>5&&n.substring(0,5).equals("open ")) {openFile(n.substring(5,nLen).trim());}
			else if(nLen>7&&n.substring(0,7).equals("delete ")) {deleteIt(n.substring(7,nLen).trim());}
			else if(nLen==7&&n.substring(0,7).equals("content")) content();
			else if(nLen>10&&n.substring(0,10).equals("createDir ")) createDir(n.substring(10,nLen).trim() );
			else if(nLen>11&&n.substring(0,11).equals("createFile "))createFile(n.substring(11,nLen).trim());
			else if(nLen==7&&n.substring(0,7).equals("go back"))return;
		}
		
	}
	public void deleteAll()
	{
			fileLen=0;
			files.clear();
			for(int i=0;i<dirLen;i++)
			{
					directories.get(i).deleteAll();
			}
			dirLen=0;
			directories.clear();
	}
	public void deleteIt(String s)
	{
		for(int i=0;i<fileLen;i++)
		{
			if(files.get(i).getName().equals(s));
			{
				fileLen--;
				Integer x=new Integer(i);
				files.remove(x);return;
			}
		}
		for(int i=0;i<dirLen;i++)
		{
			if(directories.get(i).getName().equals(s));
			{
				dirLen--;
				Integer x=new Integer(i);
				directories.get(i).deleteAll();
				directories.remove(x);
				break;
			}
		}
	}
	public void goTo(String s)
	{
		if(s.indexOf('.')==-1)
		{
			for(int i=0;i<dirLen;i++)
			{
				if(directories.get(i).getName().equals(s))
				{
					directories.get(i).getInput();
					return;
				}
			}
			System.out.println("No such directory");
		}
	}
	public void createDir(String name)
	{
		for(int i=0;i<dirLen;i++)
		{
			if(directories.get(i).getName().equals(name))
			{
				System.out.println("There is already a directory with the same name");
				return;
			}
		}
		directories.add(new Directory(name));
		dirLen++;
	}
	public void createFile(String name)
	{
		for(int i=0;i<fileLen;i++)
		{
			if(files.get(i).getName().equals(name))
			{
				System.out.println("There is already a directory with the same name");
				return;
			}
		}
		files.add(new File(name));
		fileLen++;
	}
	public void openFile(String n)
	{
		for(int i=0;i<fileLen;i++)
		{
			//System.out.println(files.get(i).getName()+file.getName());
			if(files.get(i).getName().equals(n));
			{
				for(;;)
				{
					Scanner scan=new Scanner(System.in);
					String s=scan.nextLine();
					//System.out.println(s);
					if(s.equals("close file"))break;
					files.get(i).setString(files.get(i).getString()+s);
				}
				return ;
			}
		}
		System.out.println("No such file");
	}
	public void openFileRo(String s)
	{
		for(int i=0;i<fileLen;i++)
		{
			//System.out.println(s);
			if(files.get(i).getName().equals(s));
			{
				for(;;)
				{
					Scanner scan=new Scanner(System.in);
					String k=scan.nextLine();
					if(k.equals("close file"))break;
					//files.get(i).setString(files.get(i).getString()+s);
				}
				return;
			}
		}
		System.out.println("No such file");
	}
	public void read(String s)
	{
		for(int i=0;i<fileLen;i++)
		{
			if(files.get(i).getName()==s);
			{
				System.out.println(files.get(i).getString());
				return ;
			}
		}
		System.out.println("No such file");
	}
	public void content()
	{
		for(int i=0;i<dirLen;i++)
		{
			System.out.println("\\"+name+"\\"+directories.get(i).getName());
		}
		for(int i=0;i<fileLen;i++)
		{
			System.out.println("\\"+name+"\\"+files.get(i).getName());
		}
	}
	public String getName()
	{
		return name;
	}
}
