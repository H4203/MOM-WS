package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBpediaObjet {

	String URI;
	HashMap<String, List<String>> relationEtElement;

	public DBpediaObjet()
	{
		URI = "";
		relationEtElement = new HashMap<String, List<String>>();
	}

	public DBpediaObjet(String URI)
	{
		this.URI = URI;
		relationEtElement = new HashMap<String, List<String>>();
	}

	public void add(String relation, String element)
	{
		List<String> listElement = new ArrayList<String>();
		if(relationEtElement.get(relation) != null)
		{
			for( String ele : relationEtElement.get(relation) )
			{
				listElement.add(ele);
			}
		}
		listElement.add(element);
		relationEtElement.put(relation, listElement);
	}

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
				str = line.split("> <");

				relation = str[1];//.substring(1, str[1].length()-1);
				element = str[2].substring(0, str[2].length()-3);
				if(relationEtElement.containsKey(relation)) {
					this.relationEtElement.get(relation).add(element);
				}
				else {
					List<String> list = new ArrayList<String>();
					list.add(element);
					this.relationEtElement.put(relation, list);
				}
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

	public HashMap<String, List<String>> getRelationEtElement() {
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
