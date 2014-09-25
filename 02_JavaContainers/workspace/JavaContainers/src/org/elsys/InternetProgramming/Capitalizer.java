package org.elsys.InternetProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Capitalizer {
	public static void main(String []args) throws IOException {
		final InputStream inputStream = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		final String readLine = reader.readLine();
		final String capitalizedLine = readLine.toUpperCase();
		
		System.out.println(capitalizedLine);
	}
}
