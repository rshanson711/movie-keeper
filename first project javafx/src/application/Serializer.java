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
		oos.flush(); //WHY DO THIS?
		oos.close(); //WHY DO THIS?
	}
	
	public static void saveFileAs(ArrayList<Movie> movies) throws IOException {
		FileOutputStream fos = new FileOutputStream("saved_movies.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(movies);
		oos.flush(); //WHY DO THIS?
		oos.close(); //WHY DO THIS?
	}
	
	public static ArrayList<Movie> loadFile() throws IOException {
		FileInputStream fis = new FileInputStream("saved_movies.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Movie> savedMovies = null;
		
		try {
			savedMovies = (ArrayList<Movie>)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ois.close();
		return savedMovies;
	}
}
