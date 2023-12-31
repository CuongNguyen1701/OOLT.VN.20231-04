package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.io.InputStream;
import java.util.Scanner;

public class About {

    @FXML
    private TextFlow txtFlowContent;
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
            Text text = new Text(content.toString());
            txtFlowContent.getChildren().add(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
