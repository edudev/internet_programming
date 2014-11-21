package org.elsys.internetProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpClient {

	private static final String HTTP_METHOD_GET = "GET";
	private static final String HTTP_METHOD_POST = "POST";
	private static final String DEFAULT_ENCODING = "UTF-8";
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
		final HttpClient httpClient = new HttpClient("posttestserver.com");
		httpClient.setFollow(true);
		
		final List<HttpHeader> httpHeaders = new LinkedList<HttpHeader>();
		httpHeaders.add(new HttpHeader("content-type", "application/x-www-form-urlencoded"));
		
		final Map<String, String> userParams = getUserParams();
		final String body = HttpClient.encodeURLString(userParams);

		final HttpCharResponse response = httpClient.getResponse(HTTP_METHOD_POST, "/post.php", httpHeaders, body.getBytes());
		
		System.out.print(response.getBody());
	}

	public static String encodeURLString(Map<String, String> userParams) throws UnsupportedEncodingException {
		final StringBuilder result = new StringBuilder();
		for (Map.Entry<String, String> entry : userParams.entrySet()) {
			final String key = entry.getKey();
			final String value = entry.getValue();
			
			if (result.length() != 0) {
				result.append("&");
			}
			
			result.append(URLEncoder.encode(key, DEFAULT_ENCODING));
			result.append("=");
			result.append(URLEncoder.encode(value, DEFAULT_ENCODING));
		}
		
		return result.toString();
	}

	private static Map<String, String> getUserParams() throws IOException {
		final Map<String, String> result = new HashMap<String, String>();
		String key;
		String value;

		final InputStreamReader reader = new InputStreamReader(System.in);
		final BufferedReader in = new BufferedReader(reader);
		
		key = in.readLine();
		while (!key.isEmpty()) {
			value = in.readLine();
			
			result.put(key, value);
			key = in.readLine();
		}
		
		return result;
	}

	private HttpCharResponse getResponse(String httpMethod, String path,
			List<HttpHeader> httpHeaders, byte[] data) throws IOException {

		final HttpRequest httpRequest = new HttpRequest(httpMethod, path, httpHeaders);
		httpRequest.setHost(this.host);
		httpRequest.setData(data);

		return getResponse(httpRequest);
	}

	public HttpCharResponse getResponse(String httpMethod, String path, byte[] data) throws IOException {
		return getResponse(httpMethod, path, null, data);
	}

	public HttpCharResponse getResponse(HttpRequest httpRequest) throws IOException {
		connectToServer();

		httpRequest.sendRequest(this.socket.getOutputStream());
		HttpCharResponse response = new HttpCharResponse(this.socket.getInputStream());
		if (this.followPath && (response.getStatusCode() == 301 || response.getStatusCode() == 302)) {
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
				response = getResponse(httpRequest.getMethod(), newPath, httpRequest.getData());
			} else {
				final HttpClient newClient = new HttpClient(newHost);
				newClient.setFollow(this.followPath);
				response = newClient.getResponse(httpRequest.getMethod(), newPath, httpRequest.getData());
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
