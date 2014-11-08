package org.elsys.InternetProgramming.http;

import java.util.LinkedList;
import java.util.List;

public class CharHttpResponse {
	private static final int MAX_BODY_LENGTH = 10000;
	private String statusLine;
	private final List<HttpHeader> headers = new LinkedList<HttpHeader>();
	private char[] body;
	
	public String getStatusLine() {
		return statusLine;
	}
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
	public List<HttpHeader> getHeaders() {
		return headers;
	}
	public char[] getBody() {
		if (body == null) {
			body = new char[getContentLength()];
		}
		return body;
	}
	private int getContentLength() {
		for (HttpHeader next : this.headers) {
			if (next.getName().toLowerCase().equals("content-length")) {
				final int size = Integer.parseInt(next.getValue());
				return Math.min(size, MAX_BODY_LENGTH);
			}
		}

		return 0;
	}
	public void setBody(char[] body) {
		this.body = body;
	}
}
