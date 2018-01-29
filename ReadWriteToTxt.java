import java.io.*;

public class ReadWriteToTxt 
{

	public static void main(String[] args)
	{
		
		//System.out.println(read());
		
		write();
	}
	
	public static void write()
	{
		String filename = "text.txt";
		String contentToWrite = "Hello";
		
		try
		{
			FileWriter fw = new FileWriter(filename,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contentToWrite);
			bw.newLine();
			bw.close();
		}
		catch(Exception exc)
		{
			System.out.println("Write Error...");
		}
		
		
	
	}

	public static void write(String filename,String contentToWrite)
	{
		
		try
		{
			FileWriter fw = new FileWriter(filename,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contentToWrite);
			bw.newLine();
			bw.close();
		}
		catch(Exception exc)
		{
			System.out.println("Write Error...");
		}
		
		
	
	}
	public static String read(String tempFilename)
	{
		String filename = tempFilename;
		String fileContent = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String sReadLine = br.readLine();
			
			while(sReadLine!=null)
			{
				fileContent +=sReadLine;
				sReadLine = br.readLine();	
				
			}
			
			br.close();
			
		}
		catch(Exception exc)
		{
			System.out.println("Read Error");
		}
		
		return fileContent;
	}
	
}