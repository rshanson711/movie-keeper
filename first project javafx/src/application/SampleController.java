package application;

import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;


public class SampleController {
	
	private ArrayList<Movie> watchedMovies;
	
	@FXML
	private TextArea mainText;
	@FXML
	private TextField titleField;
	@FXML
	private TextArea movieInfoField;
	@FXML
	private ImageView posterField;
	@FXML
	private MenuItem saveAsButton;
	@FXML
	private Button addToWatchedButton;
	
	public void buttonClick() {
		System.out.println("Click");
		//mainText.setText(JSONHandler.executeGet());
	}
	
	public void searchTitle() {
		String title = titleField.getText().trim().replaceAll(" ", "+");
		String url = "https://www.omdbapi.com/?apikey=73342f23&t=" + title;
		
		Movie movie = JSONHandler.executeGet(url);
		String infoTexts[] = formSearchTitleDisplay(movie);
		
		movieInfoField.setText(infoTexts[0]);
		
		Image poster = new Image(infoTexts[1]);
		//Image poster = new Image("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg");
		posterField.setImage(poster);
		
		System.out.println(url);
	}
	
	public String[] formSearchTitleDisplay(Movie movie) {
		String outputText = movie.getTitle() + " (" + movie.getYear() + ")\n\n" + movie.getPlot() + " Directed by " + movie.getDirector() + ".";
		String posterURL = movie.getPoster();
		
		String output[] = new String[2];
		output[0] = outputText;
		output[1] = posterURL;
		return output;
	}
	

	public void startupMovieDisplay() {
		String url = "https://www.omdbapi.com/?apikey=73342f23&t=Blade+Runner";
		Movie bladeRunner = JSONHandler.executeGet(url);
		
		String infoTexts[] = formSearchTitleDisplay(bladeRunner);

		mainText.setText(infoTexts[0]);
		Image poster = new Image(infoTexts[1]);
		//Image poster = new Image("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg");
		posterField.setImage(poster);
		
		System.out.println(url);
	}
	
	public void addToWatched() {
		
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
