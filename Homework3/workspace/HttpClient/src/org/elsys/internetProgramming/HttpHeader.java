package org.elsys.internetProgramming;

public class HttpHeader {
	private final String name;
	private final String value;
	
	public HttpHeader(final String name, final String value) {
		this.name = name;
		this.value = value;
	}

	public HttpHeader(final String headerLine) {
		final String[] headerSplit = headerLine.split(":\\s?", 2);
		if (headerSplit.length != 2) {
			throw new IllegalArgumentException("Header line not valid");
		}
		
		this.name = headerSplit[0];
		this.value = headerSplit[1];
	}

	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
}
