package org.elsys.InternetProgramming;

public class Equality {
	private final int value;
	
	public Equality(int value) {
		this.value = value;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Equality) {
			Equality equality = (Equality) obj;
			return equality.value == this.value;
		}
		return false;
	}
	
	public static void main(String[] args) {
		final Equality a1 = new Equality(1);
		final Equality b1 = new Equality(1);
		final Equality c1 = a1;
		final Equality c2 = new Equality(2);
		
		System.out.println(a1 == b1);
		System.out.println(a1 == c1);
		System.out.println(a1 == c2);
		System.out.println(b1 == c1);
		System.out.println(b1 == c2);
		System.out.println(c1 == c2);

		System.out.println();
	
		System.out.println(a1.equals(b1));
		System.out.println(a1.equals(c1));
		System.out.println(a1.equals(c2));
		System.out.println(b1.equals(c1));
		System.out.println(b1.equals(c2));
		System.out.println(c1.equals(c2));
	}
}
