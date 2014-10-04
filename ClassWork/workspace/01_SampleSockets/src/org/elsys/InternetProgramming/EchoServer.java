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
	
	private class ClientHandler extends Thread {
		private final Socket clientSocket;
		
		public ClientHandler(Socket sock) {
			this.clientSocket = sock;
		}
		
		public void run() {
			try {
				handleClient(this.clientSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void handleClient(final Socket clientSocket) throws IOException {
			final InputStream inputStream = clientSocket.getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();

			final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			final BufferedReader in = new BufferedReader(inputStreamReader);
			final PrintWriter out = new PrintWriter(outputStream);

			final String readLine = in.readLine();
			if ("END".equals(readLine)) {
				started = false;
			}
			out.println(readLine);
			out.flush();
			
			clientSocket.close();
		}
	}

	public static void main(String []args) throws IOException {
		EchoServer echoServer = new EchoServer();
		echoServer.run();
	}

	private boolean started = true;

	public void run() throws IOException {
		final ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
				
		while (started ) {
			final Socket clientSocket = serverSocket.accept();

			new ClientHandler(clientSocket).start();
		}
		
		serverSocket.close();
	}
}
