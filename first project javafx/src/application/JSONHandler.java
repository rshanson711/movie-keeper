package application;
import java.net.*;
import java.io.*;

public class JSONHandler {
	
//	public static String executePost(String urlParameters) {
//		HttpURLConnection connection = null;
//
//		try {
//			//Create connection
//			URL url = new URL("http://omdbapi.com");
//			connection = (HttpURLConnection)url.openConnection();
//			connection.setRequestMethod("POST");
//			connection.setRequestProperty("Content-Type", 
//					"application/x-www-form-urlencoded");
//
//			connection.setRequestProperty("Content-Length", 
//					Integer.toString(urlParameters.getBytes().length));
//			connection.setRequestProperty("Content-Language", "en-US");  
//
//			connection.setUseCaches(false);
//			connection.setDoOutput(true);
//
//			//Send request
//			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
//			wr.writeBytes(urlParameters);
//			wr.close();
//
//			//Get Response  
//			InputStream is = connection.getInputStream();
//			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//			StringBuffer response = new StringBuffer(); // or StringBuffer if Java version 5+
//			String line;
//			while ((line = rd.readLine()) != null) {
//				response.append(line);
//				response.append('\r');
//			}
//			rd.close();
//			return response.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			if (connection != null) {
//				connection.disconnect();
//			}
//		}
//	}
	
	public static String executeGet(String urlP) {
		try {
			//URL url = new URL("https://www.omdbapi.com/?apikey=73342f23&t=Blade+Runner");
			URL url = new URL(urlP);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			
			//new JSONObject(content).toString(2);
			return content.toString();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
