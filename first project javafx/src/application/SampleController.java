package application;

import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;


public class SampleController {
	
	private ArrayList<Movie> movies;
	@FXML
	private TextArea mainText;
	@FXML
	private TextField titleField;
	@FXML
	private TextArea movieInfoField;
	@FXML
	private ImageView posterField;
	
	
	public void buttonClick() {
		System.out.println("Click");
		//mainText.setText(JSONHandler.executeGet());
	}
	
	public void searchTitle() {
		String title = titleField.getText().trim().replaceAll(" ", "+");
		String url = "https://www.omdbapi.com/?apikey=73342f23&t=" + title;
		
		String infoTexts[] = JSONHandler.executeGet(url);
		
		movieInfoField.setText(infoTexts[0]);
		
		Image poster = new Image(infoTexts[1]);
		//Image poster = new Image("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg");
		posterField.setImage(poster);
		
		System.out.println(url);
	}
	

	public void startupMovieDisplay() {
		String url = "https://www.omdbapi.com/?apikey=73342f23&t=Blade+Runner";
		
		String infoTexts[] = JSONHandler.executeGet(url);

		mainText.setText(infoTexts[0]);
		Image poster = new Image(infoTexts[1]);
		//Image poster = new Image("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg");
		posterField.setImage(poster);
		
		System.out.println(url);
	}
	
//	@FXML
//	public static String updateMainText(Movie movie) {
//		String title = movie.getTitle();
//		int year = movie.getYear();
//		String plot = movie.getPlot();
//		String director = movie.getDirector();
//		//Image poster = movie.getPoster();
//		
//		String output = title + " (" + year + ")" + "\n\n" + plot + " Directed by " + director + ".";
//		return output;
//	}
	
	
}
