package laucher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dbpedia.DBpediaSpotlightClient;

public class launcher {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String film = sc.nextLine();
		sc.close();
		
		List<String> toDisplay = new ArrayList<String>();
		DBpediaSpotlightClient d = new DBpediaSpotlightClient();
		toDisplay = d.extract(film);
		
		for(int i = 0; i < toDisplay.size(); i++) {
			System.out.println(toDisplay.get(i));
		}
	}
}
