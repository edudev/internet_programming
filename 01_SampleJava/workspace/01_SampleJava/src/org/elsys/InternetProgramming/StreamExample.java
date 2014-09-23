package org.elsys.InternetProgramming;

import java.io.IOException;
import java.io.InputStream;

public class StreamExample {
	public static void main(String []args) {
		
	}
	
	public static void inputStreamExample() throws IOException {
		InputStream input = System.in;
		
		final int b = input.read();
		
		System.out.println(Character.toString((char)b));
		
		final byte a[] = new byte[3];
		input.read(a);
		System.out.println(new String(a));
	}
}
