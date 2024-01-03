package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import model.record.Record;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;

public class Export {

    @FXML
    private Button buttonBrowse;

    @FXML
    private Button buttonExport;

    @FXML
    private Label labelSelectedDestination;

    Record record;

    public void setRecord(Record record) {
        this.record = record;
    }
    @FXML
    void handleBrowse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showSaveDialog(null);
        if(selectedFile != null){
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }
            labelSelectedDestination.setText(filePath);
        }
    }


    @FXML
    void handleExport(ActionEvent event) {
        if (record == null) {
            // Show error message popup if the record is null
            showPopup("Error", "No record to export.");
            ((Button) event.getSource()).getScene().getWindow().hide();
            return;
        }
        String destination = labelSelectedDestination.getText();
        if (destination == null || destination.isEmpty()) {
            // Show error message popup if the destination is empty
            showPopup("Error", "Destination is not specified.");
            return;
        }
        record.export(destination);
        // Show success message popup
        showPopup("Export Successful", "Record exported successfully.");

        // Close the window
        ((Button) event.getSource()).getScene().getWindow().hide();
    }

    private void showPopup(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}

