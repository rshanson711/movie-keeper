package application;

import java.io.*;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;

public class Serializer implements Serializable {
	private static final long serialVersionUID = -7301187304159624983L;
	
	@FXML
	private MenuItem saveAsButton;
	@FXML
	private MenuItem saveButton;
	@FXML
	private Button loadButton;
	
	public static void saveFile(ArrayList<Movie> movies) throws IOException {
		FileOutputStream fos = new FileOutputStream("saved_movies.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(movies);
		oos.flush();
		oos.close();
	}
	
	public static void saveFileAs(ArrayList<Movie> movies) throws IOException {
		FileOutputStream fos = new FileOutputStream("saved_movies.txt");
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
