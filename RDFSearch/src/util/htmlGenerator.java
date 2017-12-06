package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class htmlGenerator {
	String codeHtml;
	//A Ajouter en parametre : nom du film, url de l'affiche du film, les donnees qui definissent le film, les acteurs avec image, films associes avec nom et url de l'image
	htmlGenerator(String titreFilm, String URLFilm, HashMap<String,String> donnees, HashMap<String,String> acteurs, HashMap<String,String> filmsAssocies){
		codeHtml = "<!DOCTYPE html>\n" + 
				"<!-- Template by html.am -->\n" + 
				"<html>\n" + 
				"	<head>\n" + 
				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" + 
				"		<title>3 Column, Right Menus</title>\n" + 
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
				"					<h1>" + titreFilm + "</h1>\n" + 
				"				</div>\n" + 
				"			</header>\n" + 
				"		<div id=\"mainwrapper\">\n" + 
				"		\n" + 
				"			<div id=\"contentwrapper\">\n" + 
				"				<div id=\"content\">\n" + 
				"					<div class=\"innertube\">\n" + 
				"						<h1>Heading</h1>\n";
				
				Set<String> cles = donnees.keySet();
				Iterator<String> it = cles.iterator();
				while(it.hasNext()) {
					String cle = it.next();
					String valeur = donnees.get(cle);
					codeHtml += "<p>" + cle + " : " + valeur + "</p>";
				}
				
				codeHtml+= "</div>\n" + 
				"				</div>\n" + 
				"			</div>\n" + 
				"			\n" + 
				"			<nav id=\"leftmenu\">\n" + 
				"				<div class=\"innertube\">\n" + 
				"					<p><script type=\"text/javascript\">generateText(5)</script></p>\n" + 
				"				</div>\n" + 
				"			</nav>\n" + 
				"			\n" + 
				"			<nav id=\"rightmenu\">\n" + 
				"				<div class=\"innertube\">\n" + 
				"					<h3>Right heading</h3>\n" + 
				"					<ul>\n" + 
				"						<li><a href=\"#\">Link 1</a></li>\n" + 
				"						<li><a href=\"#\">Link 2</a></li>\n" + 
				"						<li><a href=\"#\">Link 3</a></li>\n" + 
				"						<li><a href=\"#\">Link 4</a></li>\n" + 
				"						<li><a href=\"#\">Link 5</a></li>\n" + 
				"					</ul>\n" + 
				"					<h3>Right heading</h3>\n" + 
				"					<ul>\n" + 
				"						<li><a href=\"#\">Link 1</a></li>\n" + 
				"						<li><a href=\"#\">Link 2</a></li>\n" + 
				"						<li><a href=\"#\">Link 3</a></li>\n" + 
				"						<li><a href=\"#\">Link 4</a></li>\n" + 
				"						<li><a href=\"#\">Link 5</a></li>\n" + 
				"					</ul>\n" + 
				"					<h3>Right heading</h3>\n" + 
				"					<ul>\n" + 
				"						<li><a href=\"#\">Link 1</a></li>\n" + 
				"						<li><a href=\"#\">Link 2</a></li>\n" + 
				"						<li><a href=\"#\">Link 3</a></li>\n" + 
				"						<li><a href=\"#\">Link 4</a></li>\n" + 
				"						<li><a href=\"#\">Link 5</a></li>\n" + 
				"					</ul>\n" + 
				"				</div>\n" + 
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
}
