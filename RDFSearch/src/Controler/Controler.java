package Controler;

import Request.Search;

public class Controler 
{
	public static void main(String[] args)
	{
		Search search = new Search();
		
		try
		{	
			//String html = search.getPageHTML("Titanic", "google");
			
			//search.storeString(html, "result.txt");
			
			String imageLink = search.getImageLink("Titanic");
			
			search.storeString(imageLink, "TitanicFilmImageLink.txt");
		}
		catch (Exception e)
		{
			
		}
	}
}
