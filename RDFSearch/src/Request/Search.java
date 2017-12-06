package Request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.io.IOException;
import java.util.logging.*;
import org.jsoup.*;	
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Search
{
	public void getImage(String query) throws IOException
	{
		launchQuery(query, "qwantImage");
		
		
	}
	
	public void getHTML(String query, String engine, String destination) throws IOException
	{
		launchQuery(query, engine);
		//storeResult(bReader, destination);
	}
	
	public void launchQuery(String query, String engine) throws IOException
	{
		try 
		{
			String url;
			
			if (engine == "google")
	    	{
	    		url = "https://www.google.fr/search?q=" + query;
	    	}
	    	else if (engine == "qwant")
	    	{
	    		url = "https://www.qwant.com/?q=" + query + "&t=all";
	    	}
	    	else if (engine == "qwantImage")
	    	{
	    		url = "https://www.qwant.com/?q=" + query + "&t=images";
	    	}
	    	else
	    	{
	    		url = "https://www.google.fr/search?q=" + query;
	    	}
			
			Document doc = Jsoup.connect(url).get();
			
			Elements paragraphs = doc.select("p");
			
			for (Element p : paragraphs)
			{
				System.out.println(p.text());
			}
		} 
		catch (IOException ex) 
		{

		}
	}

		/*URL url = null;
		URLConnection conn;
		BufferedReader bReader = null;
		
	    try 
	    {	
	    	if (engine == "google")
	    	{
	    		url = new URL("https://www.google.fr/search?q=" + query);
	    	}
	    	else if (engine == "qwant")
	    	{
	    		url = new URL("https://www.qwant.com/?q=" + query + "&t=all");
	    	}
	    	else if (engine == "qwantImage")
	    	{
	    		url = new URL("https://www.qwant.com/?q=" + query + "&t=images");
	    	}
	    	else
	    	{
	    		url = new URL("https://www.google.fr/search?q=" + query);
	    	}
	    	
	        conn = url.openConnection();
	        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
	        conn.connect();
	           
	        bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    } 
	    catch (MalformedURLException mue) 
	    {
	         mue.printStackTrace();
	    }
	    catch (IOException ioe) 
	    {
	         ioe.printStackTrace();
	    }*/
	    
	    //return bReader;
	
	public void storeResult(BufferedReader bReader, String destination)
	{
		try
		{
			PrintWriter out = new PrintWriter(destination);
		
			String line;
			
			while ((line = bReader.readLine()) != null) 
	        {
				for (int i = 0; i < line.length(); i = i + 1)
				{
					if (line.charAt(i) == '>')
					{
						line = line.substring(0, i+1) + "\n" + line.substring(i + 1, line.length());
						i = i + 1;
					}
				}
				
				out.println(line); 
	        }
			
			out.close();
		}
		catch (Exception e)
		{
			
		}
	}
}