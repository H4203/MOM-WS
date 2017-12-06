package Controler;

import Request.Search;

public class Controler 
{
	public static void main(String[] args)
	{
		Search search = new Search();
		
		try
		{	
			search.launchQuery("test");
			
			search.storeResult("result.txt");
		}
		catch (Exception e)
		{
			
		}
	}
}
