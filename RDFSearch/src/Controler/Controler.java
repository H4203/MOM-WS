package Controler;

import Request.Search;

public class Controler 
{
	public static void main(String[] args)
	{
		Search search = new Search();
		
		try
		{	
			search.getHTML("Titanic Film", "google", "result.txt");
		}
		catch (Exception e)
		{
			
		}
	}
}
