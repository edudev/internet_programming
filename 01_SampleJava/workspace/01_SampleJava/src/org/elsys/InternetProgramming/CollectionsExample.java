package org.elsys.InternetProgramming;

import java.util.ArrayList;

public class CollectionsExample {
	private static final int SIZE = 9;
	
	public static void main(String[] args) {
		arrayNumbers();
		arrayNumbersInitialization();
		
		iterateArrayNumbers();
		iterateArrayIterator();
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

	public static void listInitlalization() {
		final ArrayList<String> list = new ArrayList<String>();
		
		list.add("First element");
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

}
