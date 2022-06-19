package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;


public class SampleController implements Initializable {
	
	private ArrayList<Movie> allWatchedMovies = new ArrayList<Movie>();
	private ArrayList<Movie> allPlannedMovies = new ArrayList<Movie>();
	private ObservableList<Movie> watchedMoviesData = FXCollections.observableArrayList();
	private ObservableList<Movie> plannedMoviesData = FXCollections.observableArrayList();
	private ArrayList<Movie> currentSessionWatchedMovies = new ArrayList<Movie>();
	private ArrayList<Movie> currentSessionPlannedMovies = new ArrayList<Movie>();
	private Movie lastSearchedMovie;
	
	@FXML
	private TextArea mainText;
	@FXML
	private TextField titleField;
	@FXML
	private TextArea movieInfoField;
	@FXML
	private ImageView posterField;
	@FXML
	private MenuItem saveButton;
	@FXML
	private MenuItem saveAsButton;
	@FXML
	private Button loadButton;
	@FXML
	private Button addToTableButton;
	@FXML
	private Button removeFromTableButton;
	@FXML
	private TableView<Movie> watchedTableView;
	@FXML
	private TableView<Movie> plannedTableView;
	@FXML
	private TabPane listTabPane;	
	@FXML
	private Tab watchedTab;
	@FXML
	private Tab plannedTab;
	@FXML
	private TableColumn<Movie, String> watchedTitleColumn;
	@FXML
	private TableColumn<Movie, Integer> watchedYearColumn;
	@FXML
	private TableColumn<Movie, String> watchedDirectorColumn;
	@FXML
	private TableColumn<Movie, String> plannedTitleColumn;
	@FXML
	private TableColumn<Movie, Integer> plannedYearColumn;
	@FXML
	private TableColumn<Movie, String> plannedDirectorColumn;
	
	
	@FXML
	void searchTitle() {
		String title = titleField.getText().trim().replaceAll(" ", "+");
		String url = "https://www.omdbapi.com/?apikey=73342f23&t=" + title;
		
		Movie movie = JSONHandler.executeGet(url);
		lastSearchedMovie = movie;
		
		String infoTexts[] = formSearchTitleDisplay(movie);
		
		movieInfoField.setText(infoTexts[0]);
		
		Image poster = new Image(infoTexts[1]);
		//Image poster = new Image("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg");
		posterField.setImage(poster);
		
		System.out.println(url);
		mainText.setText(url);
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
	
	public int getCurrentTab() {
		
		/* listTabPane indices:
		 * 0 == Watched
		 * 1 == Planned
		 */
		
		return listTabPane.getSelectionModel().getSelectedIndex();
	}
	
	@FXML
	void addToTable() {
		int currentTab = getCurrentTab();
		
		if (currentTab == 0 && !watchedMoviesData.contains(lastSearchedMovie)) {
			allWatchedMovies.add(lastSearchedMovie);
			watchedMoviesData.add(lastSearchedMovie);
			currentSessionWatchedMovies.add(lastSearchedMovie);
			System.out.println("Added movie to Watched.");
		} else if (currentTab == 1 && !plannedMoviesData.contains(lastSearchedMovie)) {
			allPlannedMovies.add(lastSearchedMovie);
			plannedMoviesData.add(lastSearchedMovie);
			currentSessionPlannedMovies.add(lastSearchedMovie);
			System.out.println("Added movie to Planned.");
		} else {
			System.out.println("Movie already added to list!");
		}
	}
	
	@FXML
	void removeFromTable() {
		/* LOOK INTO A WAY TO OPTIMIZE THIS FUNCTION SOMEHOW
		 * IT SEEMS LIKE IT CAN BE WRITTEN "BETTER"
		 */
		
		int currentTabIndex = getCurrentTab();
		TableView<Movie> currentTableView;
		
		if (currentTabIndex == 0) {
			currentTableView = watchedTableView;
		} else {
			currentTableView = plannedTableView;
		}
		
		Movie selectedMovie = currentTableView.getSelectionModel().getSelectedItem();
		
		
		if (selectedMovie != null) {
			if (currentTabIndex == 0) {
				allWatchedMovies.remove(selectedMovie);
				watchedMoviesData.remove(selectedMovie);
				currentSessionWatchedMovies.remove(selectedMovie);
				System.out.println("Movie removed.");
			} else if (currentTabIndex == 1) {
				allPlannedMovies.remove(selectedMovie);
				plannedMoviesData.remove(selectedMovie);
				currentSessionPlannedMovies.remove(selectedMovie);
			}
		} else {
			System.out.println("Failed to remove movie.");
		}
	}
	
	public void overwriteSave(int selection) {
		switch (selection) {
		case 0:
			try {
				Serializer.saveFile(allWatchedMovies, selection);
				System.out.println("Movies saved.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 1:
			try {
				Serializer.saveFile(allPlannedMovies, selection);
				System.out.println("Movies saved.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			overwriteSave(0);
			overwriteSave(1);
			break;
		}
	}
	
	public void createNewSave(int selection) {
		switch (selection) {
		case 0:
			try {
				Serializer.saveFileAs(currentSessionWatchedMovies, 0);
				System.out.println("Movies saved to a new file.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 1:
			try {
				Serializer.saveFileAs(currentSessionPlannedMovies, 1);
				System.out.println("Movies saved to a new file.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			createNewSave(0);
			createNewSave(1);
			break;
		}
		
	}
	
	
	// The following are intermediate methods for the save buttons (6 in total) -------------------
	
	@FXML
	void overwriteWatched() {
		overwriteSave(0);
	}
	
	@FXML
	void overwritePlanned() {
		overwriteSave(1);
	}
	
	@FXML
	void overwriteAll() {
		overwriteSave(2);
	}
	
	@FXML
	void createWatched() {
		createNewSave(0);
	}
	
	@FXML
	void createPlanned() {
		createNewSave(1);
	}
	
	@FXML
	void createAll() {
		createNewSave(2);
	}
	
	// --------------------------------------------------------------------------------------------
	
	
	@FXML  //Load movies from a save file
	void loadMovies() {
		try {
			ArrayList<Movie> watchedMovies = new ArrayList<Movie>(Serializer.loadWatchedFile());
			ArrayList<Movie> plannedMovies = new ArrayList<Movie>(Serializer.loadPlannedFile());
			loadSavedMoviesStartup(watchedMovies, plannedMovies);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*Use save file data to initialize the Watched column
	public void initializeWatchedColumns(ArrayList<Movie> savedMovies) {
		loadSavedMovies(savedMovies);
	}*/
	
	//Collect movies for TableView on program startup
	public void loadSavedMoviesStartup(ArrayList<Movie> watchedMovies, ArrayList<Movie> plannedMovies) {	
		watchedMoviesData.remove(0, watchedMoviesData.size());
		for(Movie movie : watchedMovies) {
			watchedMoviesData.add(movie);
			allWatchedMovies.add(movie);
		}
		
		plannedMoviesData.remove(0, plannedMoviesData.size());
		for (Movie movie : plannedMovies) {
			plannedMoviesData.add(movie);
			allPlannedMovies.add(movie);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		watchedTitleColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("Title"));
		watchedYearColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("Year"));
		watchedDirectorColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("Director"));
		watchedTableView.setItems(watchedMoviesData);
		
		plannedTitleColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("Title"));
		plannedYearColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("Year"));
		plannedDirectorColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("Director"));
		plannedTableView.setItems(plannedMoviesData);
		loadMovies();
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
