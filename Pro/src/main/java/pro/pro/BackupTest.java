package pro.pro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupTest {

    @FXML
    void backupOnAction(ActionEvent event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fileName = "Solo_Fitness_Backup_" + dtf.format(LocalDateTime.now());

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select destination to save backup");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Database file", "*.db"));
        fileChooser.setInitialFileName(fileName);
        File dbFile = fileChooser.showSaveDialog(stage);
        if (dbFile != null) {
            Path path = Paths.get("solofitness.db");
            try {
                Files.copy(path, dbFile.toPath());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void restoreOnAction(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file to restore");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Database file", "*.db"));
        File dbFile = fileChooser.showOpenDialog(stage);
        if (dbFile != null) {
            Path path = Paths.get("solofitness.db");
            try {
                Files.copy(dbFile.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
