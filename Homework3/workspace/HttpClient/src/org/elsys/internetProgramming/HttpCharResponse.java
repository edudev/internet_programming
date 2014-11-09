package org.elsys.internetProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HttpCharResponse {

	private char[] body;
	private int statusCode;
	private final List<HttpHeader> headers = new LinkedList<HttpHeader>();

	public HttpCharResponse(final InputStream inputStream) throws IOException {
		final InputStreamReader reader = new InputStreamReader(inputStream);
		final BufferedReader in = new BufferedReader(reader);
		
		final String statusLine = in.readLine();
		final String[] statusLineSplit = statusLine.split("\\s", 3);
//		final String httpVersion = statusLineSplit[0];
		this.statusCode = Integer.parseInt(statusLineSplit[1]);
//		final String statusReason = statusLineSplit[2];
		
		String headerLine = in.readLine();
		while (!headerLine.isEmpty()) {
			this.headers.add(new HttpHeader(headerLine));
			headerLine = in.readLine();
		}
		
		readBody(in);
	}

	private void readBody(final BufferedReader in) throws IOException {
		final String contentLength = this.getHeader("Content-Length");
		if (contentLength != null) {
			readFullBody(in);
			return;
		}

		final String transferEncoding = this.getHeader("Transfer-Encoding");
		if (transferEncoding != null && transferEncoding.toLowerCase().equals("chunked")) {
			readChunkedBody(in);
			return;
		}
		
		throw new IllegalStateException("HttpResponse not supported");
	}

	private void readChunkedBody(BufferedReader in) throws IOException {
		final ArrayList<Character> body = new ArrayList<Character>();
		
		int chunkSize = Integer.parseInt(in.readLine(), 16);
		while (chunkSize != 0) {			
			body.ensureCapacity(body.size() + chunkSize);

			for (int i = 0; i < chunkSize; ++i) {
				body.add((char) in.read());
			}
			
			String line = in.readLine();
			line = in.readLine();
			chunkSize = Integer.parseInt(line, 16);
		}
		
		this.body = new char[body.size()];
		for (int i = 0; i < body.size(); ++i) {
			this.body[i] = body.get(i);
		}
	}

	private void readFullBody(final BufferedReader in) throws IOException {
		final int bodyLength = Integer.parseInt(this.getHeader("Content-Length"));
		this.body = new char[bodyLength];
		in.read(this.body, 0, bodyLength);
	}

	public String getHeader(String lookUpName) {
		lookUpName = lookUpName.toLowerCase();

		for (final HttpHeader header : this.headers) {
			if (lookUpName.equals(header.getName().toLowerCase())) {
				return header.getValue();
			}
		}

		return null;
	}

	public char[] getBody() {
		return body;
	}

	public void setBody(final char[] body) {
		this.body = body;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(final int statusCode) {
		this.statusCode = statusCode;
	}

	public List<HttpHeader> getHeaders() {
		return headers;
	}

}
