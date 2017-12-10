package util;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import RDF.Request;
import dbpedia.DBpediaSpotlightClient;

public class sparqlUtil {
		sparqlUtil(String nomFilm){
			List<String> ressources = new ArrayList<String>();
			DBpediaSpotlightClient d = new DBpediaSpotlightClient();
			ressources = d.extract(nomFilm);
			
			if( (!ressources.isEmpty()) && (ressources.size() == 1)){
				String queryString = "PREFIX p: <http://dbpedia.org/property/>"
						+ "PREFIX dbpedia: <http://dbpedia.org/resource/>"
						+ "PREFIX category: <http://dbpedia.org/resource/Category:>"
						+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
						+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" + "PREFIX geo: <http://www.georss.org/georss/>"
						+

						"SELECT DISTINCT ?x ?m ?d " + "WHERE {" + "?x ?m ?d "
						+ "FILTER(?x = "+ ressources.get(0) +")}";
				String path = "./" + ressources.get(0);
				
				
				try {
					Request.doRequest(queryString, path);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//appel de l'objet de fatima
			}
		}
}
