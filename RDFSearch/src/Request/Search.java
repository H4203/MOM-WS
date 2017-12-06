package Request;

import java.io.PrintWriter;
import java.io.IOException;

import org.jsoup.*;	
import org.jsoup.nodes.*;

public class Search
{
	public String getImageLink(String query) throws IOException
	{
		Document document = getDocument(query, "qwantImage");
		
		Element tr = document.select("tr").get(4);
		Element div = tr.select("div[class=resultimgs]").get(0);
		Element img = div.select("img").get(0);
		
		return img.attr("src");
	}
	
	public String getPageHTML(String query, String engine) throws IOException
	{
		Document document = getDocument(query, engine);
		
		return document.html();
	}
	
	public Document getDocument(String query, String engine) throws IOException
	{
		Document document = null;
		
		try 
		{
			String url;
			
			if (engine == "google")
	    	{
	    		url = "https://www.google.fr/search?q=" + query + "%20Film";
	    	}
	    	else if (engine == "qwant")
	    	{
	    		url = "https://www.qwant.com/?q=" + query + "%20Film&t=all";
	    	}
	    	else if (engine == "qwantImage")
	    	{
	    		url = "https://lite.qwant.com/?q=" + query + "%20Film&t=images";
	    	}
	    	else
	    	{
	    		url = "https://www.google.fr/search?q=" + query + "%20Film";
	    	}
			
			document =  Jsoup.connect(url).get();
		} 
		catch (IOException e) 
		{
			
		}
		
		return document;
	}

	public void storeString(String html, String destination)
	{
		try
		{
			PrintWriter out = new PrintWriter(destination);
		
			out.print(html); 
	     
			out.close();
		}
		catch (Exception e)
		{
			
		}
	}
}