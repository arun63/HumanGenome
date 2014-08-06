import java.util.*;
import java.io.*;
public class ClientSequenceMatch
{
	public static void main(String args[])throws IOException
	{
		MatchSequence seq = new MatchSequence();
		Scanner in = new Scanner(System.in);
		//String path;
		String text = "";
		String line = "";
		BufferedReader reader;
		//path=in.nextLine();
		try
		{
			reader=new BufferedReader(new FileReader(args[0]));
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("File Not Found");
			return;
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Usage: ShowFile File");
			return;
		}
		while((line = reader.readLine())!= null)
		{
			text = text + line;
		}
		String pattern = "ACACGGAT";
		if(pattern.length()%2== 1)
		{
			System.out.println("Invalid odd length pattern");
			System.exit(0);
		}
		seq.findMatch(text,pattern);
	}
}