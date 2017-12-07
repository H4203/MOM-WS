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
			
			HashMap <String, String> donnees = new HashMap();
			donnees.put("date", "1996");
			donnees.put("state", "France");
			
			HashMap <String, String> acteurs = new HashMap();
			String actor = "Liam Neeson";
			String actorURL = search.getFirstImageLink(actor);
			acteurs.put(actor, actorURL);
			actor = "Kevin Spacey";
			actorURL = search.getFirstImageLink(actor);
			acteurs.put(actor, actorURL);
			
			HashMap <String, String> filmsAssocies = new HashMap();
			filmsAssocies.put("Die Hard", search.getFirstImageLink("Die Hard" + " Film"));

			HTMLGenerator htmlGenerator = new HTMLGenerator(title, imageLink, donnees, acteurs, filmsAssocies);
			
			search.storeString(htmlGenerator.getHTML(), "rendu.html");	
		}
		catch (Exception e)
		{
			
		}
	}
}
