package org.elsys.internetProgramming;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class HttpClient {

	private static final String HTTP_METHOD_GET = "GET";
	private final String host;
	private boolean followPath;
	private Socket socket;

	public HttpClient(String host) {
		this.host = host;
		
		connectToServer();
	}

	private void connectToServer() {
		if (this.socket != null && !this.socket.isClosed()) {
			return;
		}

		try {
			this.socket = new Socket(this.host, 80);
		} catch (IOException e) {
			this.socket = null;
		}
	}

	private void closeConnectionToServer() throws IOException {
		if (this.socket != null) {
			this.socket.close();
			this.socket = null;
		}
	}
	
	public static void main(String[] args) throws Exception {
		final HttpClient httpClient = new HttpClient("google.com");
		httpClient.setFollow(true);
		final HttpCharResponse response = httpClient.getResponse(HTTP_METHOD_GET, "/", null);
		
		System.out.print(response.getBody());
	}

	public HttpCharResponse getResponse(String httpMethod, String path, byte[] data) throws IOException {
		
		final HttpRequest httpRequest = new HttpRequest(httpMethod, path);
		httpRequest.setHost(this.host);
		httpRequest.setData(data);

		return getResponse(httpRequest);
	}

	public HttpCharResponse getResponse(HttpRequest httpRequest) throws IOException {
		connectToServer();

		httpRequest.sendRequest(this.socket.getOutputStream());
		HttpCharResponse response = new HttpCharResponse(this.socket.getInputStream());
		if (this.followPath && response.getStatusCode() == 301 || response.getStatusCode() == 302) {
			final String newLocation = response.getHeader("Location");
			if (newLocation == null) {
				throw new IllegalStateException("Got a response code requireing the new location");
			}

			String newPath;
			String newHost = null;
			try {
				final URL newURL = new URL(newLocation);
				newPath = newURL.getFile();
				if (!this.getHost().toLowerCase().equals(newURL.getHost().toLowerCase())) {
					newHost = newURL.getHost();
				}
			} catch (MalformedURLException e) {
				newPath = newLocation;
			}

			if (newHost == null) {
				return getResponse(httpRequest.getMethod(), newPath, httpRequest.getData());
			} else {
				final HttpClient newClient = new HttpClient(newHost);
				newClient.setFollow(this.followPath);
				return newClient.getResponse(httpRequest.getMethod(), newPath, httpRequest.getData());
			}
		}
		
		closeConnectionToServer();
		return response;
	}

	public void setFollow(boolean follow) {
		this.followPath = follow;
	}

	public String getHost() {
		return host;
	}

}
