package org.elsys.InternetProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamExample {
	public final static String END_READER = "END";
	
	public static void main(String []args) throws IOException {
		//inputStreamExample();
		bufferedReaderExample();
	}
	
	public static void inputStreamExample() throws IOException {
		InputStream input = System.in;
		
		final int b = input.read();
		
		System.out.println(Character.toString((char)b));
		
		final byte a[] = new byte[3];
		input.read(a);
		System.out.println(new String(a));
	}
	
	public static void bufferedReaderExample() throws IOException {
		final InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		final String wholeLine = reader.readLine();
		System.out.println(wholeLine);
		
		int count = 0;
		while(!reader.readLine().equals(END_READER)) {
			++count;
		}
		System.out.println("You have entered: " + count + " lines.");
	}
}
