package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.InputStream;
import java.util.Scanner;

public class About {

    @FXML
    private Label lblAbout;
    @FXML
    private void initialize(){
        String filepath = "../texts/about.txt";
        try (InputStream inputStream = getClass().getResourceAsStream("/texts/about.txt");
             Scanner scanner = new Scanner(inputStream)) {

            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }

            // Set the content to the Label
            lblAbout.setText(content.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
