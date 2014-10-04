package org.elsys.InternetProgramming;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AssociativeContainer {
	public static void main(String []args) {
		final Map<String, Integer> countryPopulation = new HashMap<String, Integer>();
		
		countryPopulation.put("Bulgaria", 8);
		countryPopulation.put("Germany", 80);
		countryPopulation.put("France", 100);
		countryPopulation.put("US", 300);
		countryPopulation.put("Greece", 11);
		
		for (Entry<String, Integer> entry : countryPopulation.entrySet()) {
			if (entry.getValue() > 10) {
				System.out.println(entry.getKey() + " has population of " + entry.getValue() + " million.");
			}
		}
	}
}
