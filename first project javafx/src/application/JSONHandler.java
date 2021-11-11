package application;
import java.net.*;
import java.io.*;
import com.google.gson.*;

public class JSONHandler {
	
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
			String jsonData = content.toString();
			JsonObject jsonEle = JsonParser.parseString(jsonData).getAsJsonObject();
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonOutput = gson.toJson(jsonEle);
			
			
			return jsonOutput;
			//return content.toString();
			
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
