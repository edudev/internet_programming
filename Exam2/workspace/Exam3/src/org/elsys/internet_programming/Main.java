package org.elsys.internet_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		final int lowerBound = getIntegerFromUser("Въведете долна граница: ");
		final int upperBound = getIntegerFromUser("Въведете горна граница");
		
		final String message = "from=" + lowerBound + "&to=" + upperBound;
		final int response = getResponseFromServer(message);		

		System.out.println("Псевдо-случайно число: " + response);
	}

	private static int getResponseFromServer(String message) throws UnknownHostException, IOException {
		final Socket socket = new Socket("172.16.18.88", 12345);
		
		final InputStreamReader reader = new InputStreamReader(socket.getInputStream());
		final BufferedReader in = new BufferedReader(reader);
		
		final PrintWriter out = new PrintWriter(socket.getOutputStream());
		
		out.println("POST /random HTTP/1.0");
		out.println("Host: test.local");
		out.println("Content-type: application/x-www-form-urlencoded");
		out.println("Content-length: " + message.length());
		out.println();
		
		out.println(message);
		
		out.flush();
		socket.getOutputStream().flush();
		
		String response = in.readLine();
		while(!response.isEmpty()) {
			response = in.readLine();
		}

		response = in.readLine();
		final String[] split = response.split(": ");
		
		final int result = Integer.parseInt(split[1]);

		socket.close();
		
		return result;
	}

	private static int getIntegerFromUser(String message) throws NumberFormatException, IOException {
		System.out.println(message);
		
		final InputStreamReader reader = new InputStreamReader(System.in);
		final BufferedReader in = new BufferedReader(reader);
		
		return Integer.parseInt(in.readLine());
	}
}
