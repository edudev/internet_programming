package org.elsys.InternetProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DateFormatterClient {

	private static final String SERVER_HOST = "localhost";
	private static final int SERVER_PORT = 44002;

	public static void main(String[] args) {
		final String date = getDateFromUser();

		final ServerHandler server = new ServerHandler(SERVER_HOST, SERVER_PORT);
		server.sendDateToServer(date);
		final int daysCount = server.getCountFromServer();
		server.closeConnection();

		showCountToUser(daysCount);
	}

	private static String getDateFromUser() {
		final InputStream inputStream = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private static void showCountToUser(int daysCount) {
		System.out.println(daysCount);
	}

}
