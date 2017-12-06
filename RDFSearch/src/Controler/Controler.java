package Controler;

import java.util.ArrayList;
import java.util.List;

import Request.Search;
import dbpedia.DBpediaSpotlightClient;
import util.HTMLGenerator;

public class Controler 
{
	public static void main(String[] args)
	{
		Search search = new Search();
		
		try
		{	
			String title = "Titanic";
			//String title = "Die Hard";
			
			List<String> toDisplay = new ArrayList<String>();
			
			DBpediaSpotlightClient d = new DBpediaSpotlightClient();
			
			toDisplay = d.extract(title);
			
			for(int i = 0; i < toDisplay.size(); i++) 
			{
				System.out.println(toDisplay.get(i));
			}
			
			String imageLink = search.getFirstImageLink(title);

			HTMLGenerator htmlGenerator = new HTMLGenerator(title, imageLink, null, null, null);
			
			search.storeString(htmlGenerator.getHTML(), "rendu.html");	
		}
		catch (Exception e)
		{
			
		}
	}
}
