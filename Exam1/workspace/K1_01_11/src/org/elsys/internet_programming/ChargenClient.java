package org.elsys.internet_programming;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChargenClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		final char [] buffer = new char[10];
		final Socket connection = new Socket("172.16.18.89", 19);
		
		final InputStream inputStream = connection.getInputStream();
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		
		inputStreamReader.read(buffer, 0, 10);
		
		connection.close();

		System.out.println(buffer);
	}

}
