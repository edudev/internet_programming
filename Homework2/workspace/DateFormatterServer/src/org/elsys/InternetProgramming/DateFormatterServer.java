package org.elsys.InternetProgramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DateFormatterServer {

	private static final int SERVER_PORT = 44002;

	public static void main(String[] args) {
		try {
			new DateFormatterServer().run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ServerSocket serverSocket;
	private boolean exitServer = false;

	private void run() throws IOException {
		this.serverSocket = new ServerSocket(SERVER_PORT);
		
		while (!this.exitServer) {
			Socket clientSocket = this.serverSocket.accept();
			
			new ClientHandler(clientSocket).start();;
		}
	}

}
