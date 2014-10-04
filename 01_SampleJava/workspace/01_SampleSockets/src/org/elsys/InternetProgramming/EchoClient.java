package org.elsys.InternetProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

	private static final String SERVER = "localhost";
	private static final int PORT = 44002;
	private String request;

	public EchoClient(String line) {
		this.request = line;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Enter line");
		
		final InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		final String request = reader.readLine();
		
		final EchoClient echoClient = new EchoClient(request);
		final String response = echoClient.send();
		
		System.out.println("Response: " + response);
		
		if (!response.equals(request)) {
			System.out.println("Response different from request");
		}
	}

	private String send() throws UnknownHostException, IOException {
		Socket clientSocket = new Socket(SERVER, PORT);
		
		final InputStream inputStream = clientSocket.getInputStream();
		final OutputStream outputStream = clientSocket.getOutputStream();
		
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader in = new BufferedReader(inputStreamReader);
		
		final PrintWriter out = new PrintWriter(outputStream);
		
		out.println(this.request);
		out.flush();
		
		final String result = in.readLine();
		
		clientSocket.close();

		return result;
	}

}
