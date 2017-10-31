package javaaa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

public class Proxysetting {

	public static void main(String[] args) throws Exception {
		System.setProperty("http.proxyHost", "proxy host addr");
		System.setProperty("http.proxyPort", "808");
		Authenticator.setDefault(new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {

		        return new PasswordAuthentication("domain\\user","password".toCharArray());
		    }
		});

		URL url = new URL("http://www.google.com/");
		URLConnection con = url.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(
		                    con.getInputStream()));

		// Read it ...
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		    System.out.println(inputLine);

		in.close();

	}

}
