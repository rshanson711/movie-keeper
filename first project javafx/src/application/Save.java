package application;

import java.io.*;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class Save implements Serializable {
	
	@FXML
	private MenuItem saveAsButton;
	
	public void saveFile(ArrayList<Movie> movies) throws IOException {
		FileOutputStream fos = new FileOutputStream("saved_movies.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(movies);
		oos.flush(); //WHY DO THIS?
		oos.close(); //WHY DO THIS?
	}
}
