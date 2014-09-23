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
		
		final Fundamentals fundamentals = new Fundamentals();
		
		final String result = fundamentals.getSomething();
		System.out.println(result);
		
		fundamentals.doSomething();
		
		final String longString = fundamentals.getSomething("Smiley");
		System.out.println(longString);

		if (fundamentals.isCool(4)) {
			System.out.println("4: yes it is");
		}

		if (fundamentals.isCool(-1)) {
			System.out.println("-1: yes it is");
		}

		System.out.println(fundamentals.count(3,  5));
	}
	
	public String getSomething() {
		return "Hello World!";
	}
	
	public String getSomething(String name) {
		return "Hello " + name;
	}
	
	public void doSomething() {
		// nothing
	}
	
	public boolean isCool(int n) {
		if (n > 0) {
			return true;
		}
		return false;
	}
	
	public int count (int from, int to) {
		int result = 0;
		for (int i = from; i < to; ++i) {
			++result;
		}
		
		return result;
	}
}
