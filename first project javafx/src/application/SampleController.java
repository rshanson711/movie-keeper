package application;

import javafx.fxml.*;
import javafx.scene.control.*;


public class SampleController {
	
	@FXML
	private TextArea mainText;
	@FXML
	private TextField titleField;
	
	public void buttonClick() {
		System.out.println("Click");
		//mainText.setText(JSONHandler.executeGet());
	}
	
	public void searchTitle() {
		String title = titleField.getText().trim().replaceAll(" ", "+");
		String url = "https://www.omdbapi.com/?apikey=73342f23&t=" + title;
		
		mainText.setText(JSONHandler.executeGet(url));
		System.out.println(url);
	}
	
	
	
	@FXML
	public void updateMainText() {
		return;
	}
	
	
}
