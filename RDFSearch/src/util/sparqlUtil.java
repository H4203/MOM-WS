package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import RDF.Request;
import Request.Search;
import dbpedia.DBpediaSpotlightClient;
import graph.DBpediaObjet;

public class sparqlUtil {
		public static void creerFiche(String nomFilm){
			List<String> ressources = new ArrayList<String>();
			DBpediaSpotlightClient d = new DBpediaSpotlightClient();
			ressources = d.extract(nomFilm);
			
			if( (!ressources.isEmpty()) && (ressources.size() == 1)){
				
				//Récupération de tous les triplets
				String queryString = "PREFIX p: <http://dbpedia.org/property/> "
						+ "PREFIX dbpedia: <http://dbpedia.org/resource/> "
						+ "PREFIX category: <http://dbpedia.org/resource/Category:> "
						+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
						+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> " 
						+ "PREFIX geo: <http://www.georss.org/georss/> "
						+ "SELECT DISTINCT ?x ?m ?d " 
						+ "WHERE {" + "?x ?m ?d "
						+ "FILTER(?x = <"+ ressources.get(0) +">)}";
				
				String[] ressourceSplitted = ressources.get(0).split("/");
				String path = "./" + ressourceSplitted[ressourceSplitted.length-1].replace("_", " ");
				
				
				
				
				try {
					Request.doRequest(queryString, path);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				DBpediaObjet listeTriplet = new DBpediaObjet(ressources.get(0));
				listeTriplet.addFromFile(path);
				
				HashMap<String, List<String> > relations = listeTriplet.getRelationEtElement();
				
				//Titre du film
				String[] parse = ressources.get(0).split("/");
				String titreFilm = parse[parse.length-1].replace("_", " ");
				//System.out.println(titreFilm);
				
				//URL wikipedia du film
				String URLFilm = null;
				if(relations.containsKey("http://dbpedia.org/ontology/thumbnail")) {
					URLFilm = relations.get("http://dbpedia.org/ontology/thumbnail").get(0);
				}
				
				//Donnees
				
				HashMap<String,String> donnees = new HashMap<String,String>();
				
				if(relations.containsKey("http://dbpedia.org/ontology/director")) {
					String[] parseDirector = relations.get("http://dbpedia.org/ontology/director").get(0).split("/");
					donnees.put("Realisateur", parseDirector[parseDirector.length-1].replaceAll("_", " "));
				}
				
				if(relations.containsKey("http://xmlns.com/foaf/0.1/isPrimaryTopicOf")) {
					donnees.put("Page wikipedia", relations.get("http://xmlns.com/foaf/0.1/isPrimaryTopicOf").get(0));
				}
				else {
					donnees.put("image", null);
				}
				
				if(relations.containsKey("http://dbpedia.org/ontology/genre")) {
					String[] parseGenre = relations.get("http://dbpedia.org/ontology/genre").get(0).split("/");
					donnees.put("Genre", parseGenre[parseGenre.length-1].replaceAll("_", " "));
				}
				
				if(relations.containsKey("http://dbpedia.org/ontology/abstract")) {
					donnees.put("Resume", relations.get("http://dbpedia.org/ontology/abstract").get(0));
				}
				System.out.println("");
				
				
				
				//Acteurs
				HashMap <String, String> acteurs = new HashMap<String, String>();
				List<String> listeActeurs = listeTriplet.getRelationEtElement().get("http://dbpedia.org/ontology/starring");
				if(listeTriplet.getRelationEtElement().containsKey("http://dbpedia.org/ontology/starring"))
				{
					for(int i = 0; i < listeActeurs.size(); i++) {
						String[] aAjouter = listeActeurs.get(i).split("/");
						String lActeur =aAjouter[aAjouter.length-1].replace("_", " ");
						String URLimage = null;
						try {
							if(Search.getImageLink(lActeur) != null)
								URLimage = Search.getImageLink(lActeur);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						if(URLimage != null)
							acteurs.put(lActeur, URLimage);
						else
							acteurs.put(lActeur, "");
					}
				
				}
				//Film Asssocie
				String query = "select distinct ?x ?m ?d\n" + 
						"where {\n" + 
						"?e ?a ?d \n" + 
						"FILTER(?e = <"+ ressources.get(0) +">) \n" +
						"FILTER(?a = <http://dbpedia.org/ontology/wikiPageWikiLink>) .\n" +
						"?d ?m ?x\n" + 
						"FILTER(?m = <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>)\n" +
						"FILTER(?x = <http://schema.org/Movie>) .\n" + 
						"}";
				try {
					Request.doRequest(query, "./temp");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DBpediaObjet listeTriplet2 = new DBpediaObjet(ressources.get(0));
				listeTriplet2.addFromFile("./temp");
				
				HashMap<String, List<String> > films = listeTriplet2.getRelationEtElement();
				List<String> s = films.get("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
				HashMap <String, String> filmsAssocies = new HashMap<String, String>();
				for(int i = 0; i < s.size(); i++) {
					String[] aAjouter = s.get(i).split("/");
					String leFilm =aAjouter[aAjouter.length-1].replace("_", " ");
					String URLimage = null;
					try {
						if(Search.getImageLink(leFilm) != null)
							URLimage = Search.getImageLink(leFilm);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(URLimage != null)
						filmsAssocies.put(leFilm, URLimage);
					else
						filmsAssocies.put(leFilm, "");
				}
				
				HTMLGenerator html = new HTMLGenerator(titreFilm,URLFilm,donnees, acteurs, filmsAssocies);
				
				String code = html.getHTML();
				
				FileOutputStream fop = null;
				FileWriter file = null;
				
				try {
					file = new FileWriter(new File(path + ".html"), false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					file.write(code);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("coucou");
					e.printStackTrace();
				}
				
				try {
					file.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		public static void main(String[] args) {
			sparqlUtil.creerFiche("Avatar");
		}
}
