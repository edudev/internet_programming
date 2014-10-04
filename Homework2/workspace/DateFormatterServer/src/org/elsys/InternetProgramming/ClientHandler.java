package org.elsys.InternetProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler extends Thread {

	private Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		final String line = this.getLine();
		final Date date = parseDate(line);
		final long days = getDaysFromToday(date);
		this.writeCountToClient(days);

		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeCountToClient(long days) {
		try {
			final OutputStream outputStream = this.socket.getOutputStream();
			final PrintWriter out = new PrintWriter(outputStream);
						
			out.println(days);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static long getDaysFromToday(Date date) {
		final Date now = new Date();
		
		final long diff = date.getTime() - now.getTime();
		final long days = diff / 1000 / 60 / 60 / 24;
		
		return days;
	}

	private static Date parseDate(final String str) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getLine() {
		try {
			final InputStream inputStream = this.socket.getInputStream();
			final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			final BufferedReader in = new BufferedReader(inputStreamReader);
			
			final String line = in.readLine();
			
			return line;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
}
