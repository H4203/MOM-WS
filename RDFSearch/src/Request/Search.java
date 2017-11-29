package Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Search
{
	private BufferedReader br;
	
	public void launchQuery(String query) throws IOException
	{
		URL url;
		URLConnection conn;
	    String line;

	    try 
	    {	
	        url = new URL("https://www.google.fr/search?q=" + query);
	    	
	        conn = url.openConnection();
	        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
	        conn.connect();
	           
	        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    } 
	    catch (MalformedURLException mue) 
	    {
	         mue.printStackTrace();
	    }
	    catch (IOException ioe) 
	    {
	         ioe.printStackTrace();
	    }
	}
	
	public void storeResult(String filePath)
	{
		try
		{
			PrintWriter out = new PrintWriter(filePath);
		
			String line;
			
			while ((line = br.readLine()) != null) 
	        {
				out.println(line); 
	        }
		}
		catch (Exception e)
		{
			
		}
	}
}