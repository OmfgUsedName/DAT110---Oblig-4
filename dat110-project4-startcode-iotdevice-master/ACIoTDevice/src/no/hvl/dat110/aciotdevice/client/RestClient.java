package no.hvl.dat110.aciotdevice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;


public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		URL url;
		String write = "{"
        		+ "\"message\": \"" + message + "\""
        		+ "}";
		System.out.println(write);
		try {
			url = new URL("http://localhost:8080/accessdevice/log");
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod("POST"); // PUT is another valid option
			http.setDoOutput(true);
			http.addRequestProperty("Content-Type",  "application/" + "POST");
			http.setRequestProperty("Content-Length", Integer.toString(write.length()));
			http.getOutputStream().write(write.getBytes("UTF8"));
			System.out.println(http.getResponseCode());
			System.out.println(http.getResponseMessage());
			http.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String codepath = "/accessdevice/code";

	public AccessCode doGetAccessCode() {

		AccessCode code = new AccessCode();

		// TODO: implement a HTTP GET on the service to get current access code
		String line = null;
		URL url;
		try {
			url = new URL("http://localhost:8080/accessdevice/code");
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod("GET"); // PUT is another valid option
			http.setDoOutput(true);
			http.connect();
			BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream()));
			line = rd.readLine();
			rd.close();
			http.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = line.replaceAll("[^\\d.]", "");
		char[] charArray = line.toCharArray();
		int[] intArray = new int[charArray.length];
		for(int i = 0; i<charArray.length;i++) {
			intArray[i] = (int) charArray[i] - 48;
		}
		code.setAccesscode(intArray);
		return code;
	}
}
