package controller;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Home {

    @FXML
    private Menu aboutMenu;

    @FXML
    private Menu helpMenu;

    @FXML
    private Button key001;

    @FXML
    private Button key002;

    @FXML
    private Button key003;

    @FXML
    private Button key004;

    @FXML
    private Button key005;

    @FXML
    private Button key006;

    @FXML
    private Button key007;

    @FXML
    private Button key008;

    @FXML
    private Button key009;

    @FXML
    private Button key010;

    @FXML
    private Button key011;

    @FXML
    private Button key012;

    @FXML
    private Button key013;

    @FXML
    private Button key014;

    @FXML
    private Button key015;

    @FXML
    private Button key016;

    @FXML
    private Button key017;

    @FXML
    private Button key018;

    @FXML
    private Button key019;

    @FXML
    private Button key020;

    @FXML
    private Button key021;

    @FXML
    private Button key022;

    @FXML
    private Button key023;

    @FXML
    private Button key024;

    @FXML
    private Button key025;

    @FXML
    private Button key026;

    @FXML
    private Button key027;

    @FXML
    private Button key028;

    @FXML
    private Button key029;

    @FXML
    private Button key030;

    @FXML
    private Button key031;

    @FXML
    private Button key032;

    @FXML
    private Button key033;

    @FXML
    private Button key034;

    @FXML
    private Button key035;

    @FXML
    private Button key036;

    @FXML
    private Button key037;

    @FXML
    private Button key038;

    @FXML
    private Button key039;

    @FXML
    private Button key040;

    @FXML
    private Button key041;

    @FXML
    private Button key042;

    @FXML
    private Button key043;

    @FXML
    private Button key044;

    @FXML
    private Button key045;

    @FXML
    private Button key046;

    @FXML
    private Button key047;

    @FXML
    private Button key048;

    @FXML
    private Button key049;

    @FXML
    private Button key050;

    @FXML
    private Button key051;

    @FXML
    private Button key052;

    @FXML
    private Button key053;

    @FXML
    private Button key054;

    @FXML
    private Button key055;

    @FXML
    private ToggleGroup musicStyleToggle;

    @FXML
    private Pane pianoPane;

    @FXML
    private Slider volumeSlider;
    @FXML
    private void initialize() {
        // volume indicator
        volumeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            double percentage = 100.0 * newValue.doubleValue() / volumeSlider.getMax();
            String style = String.format(
                    "-track-color: linear-gradient(to top, " +
                            "-fx-accent 0%%, " +
                            "-fx-accent %1$.1f%%, " +
                            "-default-track-color %1$.1f%%, " +
                            "-default-track-color 100%%);",
                    percentage);
            volumeSlider.setStyle(style);
        });
    }

    @FXML
    private void showAboutPopup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/About.fxml"));
            AnchorPane aboutPane = fxmlLoader.load();
            Scene scene = new Scene(aboutPane);
            Stage stage = new Stage();
            stage.setTitle("About");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
