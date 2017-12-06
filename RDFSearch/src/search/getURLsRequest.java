package search;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getURLsRequest {
	
	public getURLsRequest() {}
	
	public List<String> getURLs(String requete) throws IOException{
		List<String> result = new ArrayList<String>();
		
		Document linksDoc = null; 
		linksDoc = Jsoup.connect("http://www.google.com/search?q="+requete).userAgent("Mozilla").get();
		/*Document linksDoc = Jsoup.connect("http://www.google.com/search?q=jbutton&start=10")
        .userAgent("Mozilla").get(); start = 10 to get the links of the second page, start = 20 to get the 
        links of the third page, etc... */
		Elements titles = linksDoc.select("h3.r > a");
		
		for(Element e: titles){
		    System.out.println("text: " +e.attr("href"));
		    if(e.attr("href").indexOf('u')==1) result.add(e.attr("href").substring(e.attr("href").indexOf('h')));
		  }
		
		File f = new File("C:\\Users\\Wassim\\Documents\\INSA\\4IF\\Web S�mantique\\Projet\\cacheURLs\\"+requete+"\\");
		if(!f.exists()) {
			PrintWriter writer = new PrintWriter("C:\\Users\\Wassim\\Documents\\INSA\\4IF\\Web S�mantique\\Projet\\cacheURLs\\"+requete+"\\", "UTF-8");
			for(int i=0; i<result.size();i++) {
				writer.println(result.get(i));
			}
			writer.close();
		}
		else System.out.println("research already done before");
		
		return result;
	}

}