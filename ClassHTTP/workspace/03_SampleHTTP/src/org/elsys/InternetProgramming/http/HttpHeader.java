package org.elsys.InternetProgramming.http;

public class HttpHeader {
	private final String name;
	private final String value;
	
	public HttpHeader(String headerLine) {
		final String[] split = headerLine.split(": ", 2);
		if (split.length != 2) {
			throw new IllegalAccessError("Bad header " + headerLine);
		}
		
		this.name = split[0];
		this.value = split[1];
	}

	public HttpHeader(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
