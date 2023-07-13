package pro.pro;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;

public class CompletedController {
	@FXML
    public Button btnOk;
	// Event Listener on Button.onAction
	@FXML
	public void Ok(ActionEvent event) {
		Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void Print(ActionEvent event) throws IOException {
		 String path = "C:\\Users\\Nirmala Malshan\\Desktop\\Poject\\12.pdf";
		    File file = new File(path);
		    try {
		      if (file.exists()) {
		        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);
		        pro.waitFor();
		      } else {
		        System.out.println("file does not exist");
		      }
		    } catch (Exception e) {
		      System.out.println(e);
		    }
	}
}
