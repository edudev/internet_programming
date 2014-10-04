package org.elsys.InternetProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionsExample {
	private static final int SIZE = 9;
	
	public static void main(String[] args) {
		arrayNumbers();
		arrayNumbersInitialization();
		
		iterateArrayNumbers();
		iterateArrayIterator();
		
		listInitlalization();
		mapExample();
		completeMap();
	}
	
	public static void arrayNumbers() {
		final int a[] = new int[SIZE];
		
		a[0] = 1;
		a[SIZE - 1] = 2;
		
		System.out.println(a[0]);
	}

	public static void arrayNumbersInitialization() {
		final int a[] = new int[]{1, 2, 3, 4, 5};
				
		System.out.println(a[0]);
	}
	
	public static void iterateArrayNumbers() {
		final int a[] = new int[] {1, 3, 5, 6, 7};
		
		for (int i = 0; i < a.length; i += 1) {
			final int el = a[i];
			
			System.out.println(el);
		}
	}
	
	public static void iterateArrayIterator() {
		final int a[] = new int[] {1, 3, 5, 6, 7};
		
		for (int el : a) {
			System.out.println(el);
		}
	}

	public static void listInitlalization() {
		final List<String> list = new ArrayList<String>();
		
		list.add("First element");
		list.add("Last element");
		
		System.out.println(list.get(0));
		
		for (String str : list) {
			System.out.println(str);
		}
		
		System.out.println(list.size());
	}
	
	public static void mapExample() {
		final Map<String, Integer> m = new HashMap<String, Integer>();
		
		m.put("Bulgaria", 8);
		m.put("Germany", 80);
		
		System.out.println("Bulgaria: " + m.get("Bulgaria"));
		
		for (int val : m.values()) {
			System.out.println(val);
		}
		
		for(Entry<String, Integer> en : m.entrySet()) {
			System.out.println(en.getKey() + en.getValue());
		}
	}
	
	public static void completeMap() {
		final Map<String, List<Integer>> a = new HashMap<String, List<Integer>>();
		
		final List<Integer> list = new ArrayList<Integer>();
		
		list.add(2);
		list.add(3);
		
		for (Entry<String, List<Integer>> e : a.entrySet()) {
			System.out.println("Name: " + e.getKey());
			
			for (int i : e.getValue()) {
				System.out.println(i);
			}
			System.out.println();
		}
	}

}
