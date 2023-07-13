package pro.pro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class settingsController {
	@FXML
	private AnchorPane addMember;

	// Event Listener on Button.onAction
	@FXML
	public void ChangeU_P(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("changePassword.fxml"));
		addMember.getChildren().setAll(pane);
	}
	// Event Listener on Button.onAction
	@FXML
	public void backupOnAction(ActionEvent event) throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		String fileName = "Solo_Fitness_Backup_" + dtf.format(LocalDateTime.now());

		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select destination to save backup");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Database file", "*.db"));
		fileChooser.setInitialFileName(fileName);
		File dbFile = fileChooser.showSaveDialog(stage);
		if (dbFile != null) {
			Path path = Paths.get("Solo.db");
			try {
				Files.copy(path, dbFile.toPath());
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void restoreOnAction(ActionEvent event) {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select file to restore");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Database file", "*.db"));
		File dbFile = fileChooser.showOpenDialog(stage);
		if (dbFile != null) {
			Path path = Paths.get("Solo.db");
			try {
				Files.copy(dbFile.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void ChangePak(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("packege.fxml"));
		addMember.getChildren().setAll(pane);
	}
	// Event Listener on Button.onAction
	@FXML
	public void Help(ActionEvent event) {
		// TODO Autogenerated 
		Notifications notification1 = Notifications.create()
				.title("Help")
				.text("Please contact the software company.")
				.hideAfter(Duration.seconds(8))
				.position(Pos.BOTTOM_RIGHT);
		notification1.showConfirm();
	}
}