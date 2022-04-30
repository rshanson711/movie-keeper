package application;
import java.net.*;
import java.io.*;
import com.google.gson.*;
import javafx.scene.image.*;

public class JSONHandler {
	
	public static String[] executeGet(String urlP) {
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
			//Receiving JSON-format data from HTTP request
			String movieDataJSON = content.toString();
			
			//1 JsonObject jsonEle = JsonParser.parseString(movieData).getAsJsonObject();
			//1 String jsonOutput = gson.toJson(jsonEle);
			
			Gson gson = new Gson();
			Movie movie = gson.fromJson(movieDataJSON, Movie.class);
			
			String title = movie.getTitle();
			int year = movie.getYear();
			String plot = movie.getPlot();
			String director = movie.getDirector();
			String poster = movie.getPoster();
			
			//making output strings and putting them into output array
			String outputText = title + " (" + year + ")" + "\n\n" + plot + " Directed by " + director + ".";
			String posterURL = poster;
			String output[] = new String[2];
			output[0] = outputText;
			output[1] = posterURL;
			
			
			
			//return output array
			return output;
			
			
		} catch(Exception e) { //DO SOMETHING HERE
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getTitle(String json) {
		return "";
	}
	

	

}
