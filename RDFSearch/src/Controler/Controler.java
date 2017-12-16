package Controler;

import java.util.ArrayList;
import java.util.HashMap;
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
			
			List<String> toDisplay = new ArrayList<String>();
			
			DBpediaSpotlightClient d = new DBpediaSpotlightClient();
			
			toDisplay = d.extract(title);
			
			for(int i = 0; i < toDisplay.size(); i++) 
			{
				System.out.println(toDisplay.get(i));
			}
			
			String imageLink = search.getFirstImageLink(title + " Film");
			
			HashMap <String, String> donnees = new HashMap <String, String> ();
			donnees.put("date", "1996");
			donnees.put("state", "France");
			
			HashMap <String, String> acteurs = new HashMap <String, String> ();
			
			acteurs.put("Liam Neeson", search.getFirstImageLink("Liam Neeson"));
			acteurs.put("Kevin Spacey", search.getFirstImageLink("Kevin Spacey"));
			acteurs.put("Will Smith", search.getFirstImageLink("Will Smith"));
			acteurs.put("Will Smith 2", search.getFirstImageLink("Will Smith"));
			acteurs.put("Will Smith 3", search.getFirstImageLink("Will Smith"));
			
			HashMap <String, String> filmsAssocies = new HashMap <String, String> ();
			
			filmsAssocies.put("Die Hard", search.getFirstImageLink("Die Hard" + " Film"));
			filmsAssocies.put("Die Hard 1", search.getFirstImageLink("Die Hard" + " Film"));
			filmsAssocies.put("Die Hard 2", search.getFirstImageLink("Die Hard" + " Film"));
			filmsAssocies.put("Die Hard 3", search.getFirstImageLink("Die Hard" + " Film"));
			filmsAssocies.put("Die Hard 4", search.getFirstImageLink("Die Hard" + " Film"));
			filmsAssocies.put("Die Hard 5", search.getFirstImageLink("Die Hard" + " Film"));

			HTMLGenerator htmlGenerator = new HTMLGenerator(title, imageLink, donnees, acteurs, filmsAssocies);
			
			search.storeString(htmlGenerator.getHTML(), "rendu.html");	
		}
		catch (Exception e)
		{
			
		}
	}
}
