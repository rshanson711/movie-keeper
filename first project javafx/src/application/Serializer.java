package application;

import java.io.*;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;

public class Serializer implements Serializable {
	private static final long serialVersionUID = -7301187304159624983L;
	
	@FXML
	private MenuItem saveAsButton;
	@FXML
	private MenuItem saveButton;
	@FXML
	private Button loadButton;
	
	public static void saveFile(ArrayList<Movie> movies, int selection) throws IOException {
		FileOutputStream fos = null;
		
		switch (selection) {
		case 0:
			fos = new FileOutputStream("watched_movies.save");
			break;
		case 1:
			fos = new FileOutputStream("planned_movies.save");
			break;
		}
		
		if (fos != null) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(movies);
			oos.flush();
			oos.close();
		} else {
			System.out.println("Failed to save!");
		}
	}
	
	public static void saveFileAs(ArrayList<Movie> movies, int selection) throws IOException {
		FileChooser fc = new FileChooser();
		File file = new File(System.getProperty("user.dir"));
		fc.setInitialDirectory(file);
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Save Files", "*.save"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		
		switch (selection) {
		case 0:
			fc.setTitle("Save Watched as...");
			fc.setInitialFileName("watched_movies.save");
			break;
		case 1:
			fc.setTitle("Save Planned as...");
			fc.setInitialFileName("planned_movies.save");
			break;
		}
		
		FileOutputStream fos = new FileOutputStream(fc.showSaveDialog(stage));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(movies);
		oos.flush();
		oos.close();
	}
	
	public static ArrayList<Movie> loadWatchedFile() throws IOException {
		FileInputStream fis_watched = new FileInputStream("watched_movies.save");
		ObjectInputStream ois_watched = new ObjectInputStream(fis_watched);
		ArrayList<Movie> savedWatchedMovies = null;
		
		try {
			savedWatchedMovies = (ArrayList<Movie>)ois_watched.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ois_watched.close();
		return savedWatchedMovies;
	}
	
	public static ArrayList<Movie> loadPlannedFile() throws IOException {
		FileInputStream fis_planned = new FileInputStream("planned_movies.save");
		ObjectInputStream ois_planned = new ObjectInputStream(fis_planned);
		ArrayList<Movie> savedPlannedMovies = null;
		
		try {
			savedPlannedMovies = (ArrayList<Movie>)ois_planned.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ois_planned.close();
		return savedPlannedMovies;
	}
}
