package cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import search.getURLsRequest;

public class Cache {

	static String path = "Cache\\";
	
	public static int writeNewFile(String type, String request, String result)
	{
		String filepath = path + type + "_" + request.hashCode();
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(String.valueOf(filepath),"UTF-8");
			writer.println(result);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		return 1;
		
	}
	
	public static int alreadyExist(String type, String request)
	{
	
		String filepath = path + type + "_" + request.hashCode();
		
		File f = new File(filepath);
		if(f.exists() && !f.isDirectory()) { 
		    return 1;
		}

		return 0;
	}
	
	public static String getResult(String type, String request)
	{
		String result = "";
		String filepath = path + type + "_" + request.hashCode();
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filepath));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    result = sb.toString();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return "|erreur|";
		}
		
		return result;
	}
	
	public static void main(String[] arg0)
	{
		String request = "https://www.google.fr/search?q=lala&rlz=1C1GCEA_enFR767FR767&oq=lala&aqs=chrome..69i57j69i59j69i60.3262j0j7&sourceid=chrome&ie=UTF-8";
		String result = "";
		if(alreadyExist("url", request) == 1)
		{
			result = getResult("url", request);	
			System.out.println(result);
		}
		else
		{
			result = "lalala miaomiao";
			writeNewFile("url", request, result);
		}	
	}

}