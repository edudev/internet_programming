package org.elsys.InternetProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler {

	private Socket socket;

	public ServerHandler(String serverHost, int serverPort) {
		try {
			this.socket = new Socket(serverHost, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendDateToServer(String date) {
		try {
			final OutputStream outputStream = this.socket.getOutputStream();
			final PrintWriter out = new PrintWriter(outputStream);
			out.println(date);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCountFromServer() {
		try {
			final InputStream inputStream = this.socket.getInputStream();
			final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			final BufferedReader reader = new BufferedReader(inputStreamReader);
			
			final String line = reader.readLine();
			final int result = Integer.parseInt(line);

			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public void closeConnection() {
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
