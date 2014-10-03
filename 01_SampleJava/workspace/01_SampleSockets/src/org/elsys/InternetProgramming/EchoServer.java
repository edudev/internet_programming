package org.elsys.InternetProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private static final int SERVER_PORT = 44002;

	public static void main(String []args) throws IOException {
		EchoServer echoServer = new EchoServer();
		echoServer.run();
	}

	public void run() throws IOException {
		final ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		
		final Socket clientSocket = serverSocket.accept();
		
		handleClient(clientSocket);
		
		serverSocket.close();
	}

	private void handleClient(final Socket clientSocket) throws IOException {
		final InputStream inputStream = clientSocket.getInputStream();
		final OutputStream outputStream = clientSocket.getOutputStream();

		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader in = new BufferedReader(inputStreamReader);
		final PrintWriter out = new PrintWriter(outputStream);

		final String readLine = in.readLine();
		out.println(readLine);
		out.flush();
		
		clientSocket.close();
	}
}
