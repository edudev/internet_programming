package org.elsys.InternetProgramming.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpClientExample {

	private static final String HTTP_GET_METHOD = "GET";
	private static final String PROTOCOL_VERSION = "1.1";

	public static void main(String[] args) throws UnknownHostException, IOException {
		final HttpClientExample httpClientExample = new HttpClientExample();
		CharHttpResponse response = httpClientExample.createRequest(HTTP_GET_METHOD, "/index.html", "example.com");
		
		for(HttpHeader header: response.getHeaders()) {
			System.out.println(header.getName() + ": " + header.getValue());
		}
		
		System.out.println("Body");
		System.out.println(response.getBody());
	}

	public CharHttpResponse createRequest(String method, String path, String host) throws UnknownHostException, IOException {
		final Socket clientSocket = new Socket(host, 80);
		
		final InputStream inputStream = clientSocket.getInputStream();
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedStreamReader = new BufferedReader(inputStreamReader);
		
		final OutputStream outputStream = clientSocket.getOutputStream();
		final PrintWriter printWriter = new PrintWriter(outputStream);
				
		writeRequest(printWriter, host, path, method);
		outputStream.flush();

		final CharHttpResponse result = parseResponse(bufferedStreamReader);

		clientSocket.close();
		
		return result;
	}

	private CharHttpResponse parseResponse(final BufferedReader bufferedStreamReader)
			throws IOException {
		final CharHttpResponse result = new CharHttpResponse();

		String readLine = bufferedStreamReader.readLine();
		result.setStatusLine(readLine);
		
		readLine = bufferedStreamReader.readLine();
		while(!readLine.equals("")) {
			result.getHeaders().add(new HttpHeader(readLine));
			readLine = bufferedStreamReader.readLine();
		}

		final char[] body = result.getBody();
		bufferedStreamReader.read(body);

		return result;
	}

	private void writeRequest(PrintWriter printWriter, String host,
			String path, String method) {
		
		printWriter.printf("%s %s HTTP/%s\n", method, path, PROTOCOL_VERSION);
		printWriter.printf("Host: %s\n", host);
		printWriter.printf("\n");
		
		printWriter.flush();
	}
}
