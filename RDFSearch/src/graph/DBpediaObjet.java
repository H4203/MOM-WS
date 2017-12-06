package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DBpediaObjet {
	
	String URI;
	HashMap<String, String> relationEtElement;
	
	public DBpediaObjet()
	{
		URI = "";
		relationEtElement = new HashMap<>();
	}
	
	public DBpediaObjet(String URI)
	{
		this.URI = URI;
		relationEtElement = new HashMap<>();
	}
	
	public void add(String relation, String element)
	{
		relationEtElement.put(relation, element);
	}
	
	//return the number of element added
	//if negative, there was an error after adding |the return int| elements
	public int addFromFile(String filePath)
	{	
		int nbAjoute = 0;
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
		    String line = br.readLine();
		    String uri, relation, element;
		    
		    String []str = line.split(" ");
		    uri = str[0].substring(1, str[0].length()-2);
		    this.setURI(uri);
		    
		    while (line != null) {
		    	str = line.split(" ");
		    	
		    	relation = str[1].substring(1, str[1].length()-2);
		    	element = str[2].substring(1, str[2].length()-2);
		    	this.add(relation, element);
		        line = br.readLine();
		        nbAjoute++;
		    }
		    
		    br.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return -nbAjoute;
		}
		finally
		{
			
		}
		return nbAjoute;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public HashMap<String, String> getRelationEtElement() {
		return relationEtElement;
	}
	/*
	public static void main(String[] args)
	{
		DBpediaObjet ob = new DBpediaObjet();
		ob.addFromFile("./newfile");
		System.out.println(ob.getRelationEtElement());
	}
	*/
}
