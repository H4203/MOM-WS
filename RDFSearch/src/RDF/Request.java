package RDF;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

public class Request {
	public static void doRequest(String queryString, String path) throws FileNotFoundException {

		Query query = QueryFactory.create(queryString);

		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		FileOutputStream fop = null;
		FileWriter file = null;
		try {
			ResultSet results = qexec.execSelect();
			try {
				file = new FileWriter(new File("./newfile"), false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<QuerySolution> list = ResultSetFormatter.toList(results);
			for (int i = 0; i < list.size(); i++) {
				try {
					file.write("<" + list.get(i).get("x").toString() + "> <" + list.get(i).get("m").toString() + "> <"
							+ list.get(i).get("d").toString() + "> ." + "\r\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			try {
				file.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			qexec.close();
		}

	}

	public static void main(String[] args) {
		
		String queryString = "PREFIX p: <http://dbpedia.org/property/>"
				+ "PREFIX dbpedia: <http://dbpedia.org/resource/>"
				+ "PREFIX category: <http://dbpedia.org/resource/Category:>"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" + "PREFIX geo: <http://www.georss.org/georss/>"
				+

				"SELECT DISTINCT ?x ?m ?d " + "WHERE {" + "?x ?m ?d "
				+ "FILTER(?x = <http://dbpedia.org/resource/Berlin>)}";
		try {
			doRequest(queryString, "./newfile");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
