package pro.pro;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class NotCompleteController {
	@FXML
    public Button btnOk;

	// Event Listener on Button.onAction
	@FXML
	public void Ok(ActionEvent event) {
		Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
	}
}
