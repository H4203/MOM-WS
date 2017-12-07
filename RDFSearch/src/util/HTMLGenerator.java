package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HTMLGenerator 
{
	String codeHtml;
	
	// A Ajouter en parametre : 
	//    nom du film
	//    url de l'affiche du film, 
	//    les donnees qui definissent le film, 
	//    les acteurs avec image, 
	//    films associes avec nom et url de l'image
	public HTMLGenerator(String titre, String URLAffiche, HashMap <String, String> donnees, HashMap <String, String> acteurs, HashMap <String, String> filmsAssocies)
	{
		codeHtml = "<!DOCTYPE html>\n" + 
				"<!-- Template by html.am -->\n" + 
				"<html>\n" + 
				"	<head>\n" + 
				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" + 
				"		<title>"+ titre +"</title>\n" + 
				"		<style type=\"text/css\">\n" + 
				"		\n" + 
				"			body{\n" + 
				"				margin:0;\n" + 
				"				padding:0;\n" + 
				"				font-family: Sans-Serif;\n" + 
				"				line-height: 1.5em;\n" + 
				"			}\n" + 
				"			\n" + 
				"			#header {\n" + 
				"				background: #BCCE98;\n" + 
				"				height: 100px;\n" + 
				"			}\n" + 
				"			\n" + 
				"			#header h1 {\n" + 
				"				margin: 0;\n" + 
				"				padding-top: 15px;\n" + 
				"			}\n" + 
				"			\n" + 
				"			#mainwrapper {\n" + 
				"				overflow: hidden;\n" + 
				"			}\n" + 
				"			\n" + 
				"			#contentwrapper{\n" + 
				"				float: left;\n" + 
				"				width: 100%;\n" + 
				"			}\n" + 
				"			\n" + 
				"			#content {\n" + 
				"				margin-right: 460px; /* Width of '#rightmenu' and '#leftmenu' combined. */\n" + 
				"				padding-bottom: 10010px;\n" + 
				"				margin-bottom: -10000px;\n" + 
				"			}\n" + 
				"			\n" + 
				"			#leftmenu {\n" + 
				"				float: left;\n" + 
				"				width: 250px;\n" + 
				"				margin-left: -460px; /* Width of '#rightmenu' and '#leftmenu' combined. */\n" + 
				"				padding-bottom: 10010px;\n" + 
				"				margin-bottom: -10000px;\n" + 
				"				background: #F7FDEB;\n" + 
				"			}\n" + 
				"			\n" + 
				"			#rightmenu {\n" + 
				"				float: left;\n" + 
				"				width: 210px;\n" + 
				"				margin-left: -210px;\n" + 
				"				padding-bottom: 10010px;\n" + 
				"				margin-bottom: -10000px;\n" + 
				"				background: #DAE9BC;\n" + 
				"			}\n" + 
				"			\n" + 
				"			#footer{\n" + 
				"				clear: left;\n" + 
				"				width: 100%;\n" + 
				"				background: #BCCE98;\n" + 
				"				text-align: center;\n" + 
				"				padding: 5px 0;\n" + 
				"			}\n" + 
				"			\n" + 
				"			.innertube{\n" + 
				"				margin: 15px; /* Padding for content */\n" + 
				"				margin-top: 0;\n" + 
				"			}\n" + 
				"		\n" + 
				"		\n" + 
				"			p {\n" + 
				"				color: #555;\n" + 
				"			}\n" + 
				"	\n" + 
				"			nav ul {\n" + 
				"				list-style-type: none;\n" + 
				"				margin: 0;\n" + 
				"				padding: 0;\n" + 
				"			}\n" + 
				"			\n" + 
				"			nav ul a {\n" + 
				"				color: darkgreen;\n" + 
				"				text-decoration: none;\n" + 
				"			}\n" + 
				"			\n" + 
				"		</style>\n" + 
				"		\n" + 
				"	</head>\n" + 
				"	\n" + 
				"	<body>\n" + 
				"			<header id=\"header\">\n" + 
				"				<div class=\"innertube\">\n" + 
				"					<h1>" + titre + "</h1>\n" +                  
				"				</div>\n" + 
				"			</header>\n" + 
				"		<div id=\"mainwrapper\">\n" + 
				"		\n" + 
				"			<div id=\"contentwrapper\">\n" + 
				"				<div id=\"content\">\n" + 
				"                   <br/>\n" +
				"					<div class=\"innertube\">\n" + 
				"						<IMG SRC=" + URLAffiche + " ALT=" + titre + ">\n" +
				"                       <h2>Numbers</h2>\n";

		Set<String> cles = donnees.keySet();
		Iterator<String> it = cles.iterator();
		
		codeHtml += "<ul>\n";
		
		while(it.hasNext()) 
		{
			String cle = it.next();
			String valeur = donnees.get(cle);
			
			codeHtml += "<li>" + cle + " : " + valeur + "</li>\n";
		}
				
		codeHtml+= "</div>\n" + 
				"				</div>\n" + 
				"			</div>\n" + 
				"			\n" + 
				"			<nav id=\"leftmenu\">\n" + 
				"				<div class=\"innertube\">\n" +
				"					<h2>Actors</h2>\n"; 
									 
		Set<String> cles2 = acteurs.keySet();
		Iterator<String> it2 = cles2.iterator();
		
		while(it2.hasNext()) 
		{
			String cle = it2.next();
			String valeur = acteurs.get(cle);
				
			codeHtml += "<p>" + cle + "</p>\n";
			codeHtml += "<IMG SRC=" + valeur + " ALT=" + cle + "/>\n";
		}
		
		codeHtml += "				</div>\n" + 
				"			</nav>\n" + 
				"			\n" + 
				"			<nav id=\"rightmenu\">\n" + 
				"				<div class=\"innertube\">\n" +
				"					<h2>Films Associes</h2>\n"; 
		
		Set<String> cles3 = filmsAssocies.keySet();
		Iterator<String> it3 = cles3.iterator();
		
		while(it3.hasNext()) 
		{
			String cle = it3.next();
			String valeur = filmsAssocies.get(cle);
				
			codeHtml += "<p>" + cle + "</p>\n";
			codeHtml += "<IMG SRC=" + valeur + " ALT=" + cle + "/>\n";
		}
				
		codeHtml +="	</div>\n" + 
				"			</nav>\n" + 
				"		</div>\n" + 
				"			<footer id=\"footer\">\n" + 
				"				<div class=\"innertube\">\n" + 
				"					<p>Footer...</p>\n" + 
				"				</div>\n" + 
				"			</footer>\n" + 
				"	\n" + 
				"	</body>\n" + 
				"\n" + 
				"</html>";
	}
	
	public String getHTML()
	{
		return codeHtml;
	}
}
