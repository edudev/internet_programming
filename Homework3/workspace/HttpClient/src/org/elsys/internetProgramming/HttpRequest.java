package org.elsys.internetProgramming;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HttpRequest {

	private static final String HTTP_VERSION = "HTTP/1.1";
	private String method;
	private String path;
	private byte[] data;
	private final List<HttpHeader> headers = new LinkedList<HttpHeader>();

	public HttpRequest(String httpMethod, String path) {
		this.method = httpMethod;
		this.path = path;
	}

	public void setHost(final String host) {
		this.removeHeader("Host");
		this.addHeader("Host", host);
	}

	public void addHeader(final String name, final String value) {
		this.addHeader(new HttpHeader(name, value));
	}

	public void addHeader(final HttpHeader header) {
		this.headers.add(header);
	}

	private void removeHeader(String name) {
		name = name.toLowerCase();
		Iterator<HttpHeader> iterator = this.headers.iterator();

		while (iterator.hasNext()) {
			HttpHeader header = iterator.next();
			if (header.getName().toLowerCase().equals(name)) {
				iterator.remove();
			}
		}
	}

	public void sendRequest(OutputStream outputStream) throws IOException {
		if (this.method == null || this.path == null) {
			throw new IllegalStateException("Request method or path not set!");
		}

		final PrintWriter out = new PrintWriter(outputStream);
		
		out.printf("%s %s %s\n", this.method, this.path, HTTP_VERSION);
		for (final HttpHeader header : headers) {
			out.printf("%s: %s\n", header.getName(), header.getValue());
		}
		if (this.data != null) {
			outputStream.write(data);
		}

		out.printf("\n");
		out.flush();
		outputStream.flush();
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(final String httpMethod) {
		this.method = httpMethod;
	}

	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(final byte[] data) {
		this.data = data;
	}

	public List<HttpHeader> getHeaders() {
		return headers;
	}
}
