package org.elsys.InternetProgramming;

public class Fundamentals {
	public static void main(String[] args) {		
		int[] arr = {1, 2, 3, 4};
		int i;
		
		for (i = 0; i < arr.length; ++i) {
			if (arr[i] % 2 == 0) {
				System.out.println(i);
			}
		}
		
		TestClass a = new TestClass(1);
		TestClass b = new TestClass(1);
		
		System.out.println(a == b);
		System.out.println(a.equals(b));
		
		Fundamentals fundamentals = new Fundamentals();
		fundamentals = new Fundamentals();

		Fundamentals fundamentalsFinal = new Fundamentals();
		fundamentals = new Fundamentals();
	}
	
	public String getSomething() {
		return "Hello World!";
	}
}
