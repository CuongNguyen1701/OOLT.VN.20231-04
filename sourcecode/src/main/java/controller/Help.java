package controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import  javafx.util.Duration;
import javafx.scene.text.TextFlow;

import java.io.InputStream;
import java.util.Scanner;

public class Help {
    @FXML
    private Label num;

    @FXML
    private AnchorPane pane1;

    @FXML
    private TextFlow tutorialText1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private TextFlow tutorialText2;

    @FXML
    private AnchorPane pane3;

    @FXML
    private TextFlow tutorialText3;

    @FXML
    private void initialize(){
        initText(tutorialText1,"../texts/tutorial1.txt");
        initText(tutorialText2,"../texts/tutorial2.txt");
        initText(tutorialText3,"../texts/tutorial3.txt");
    }

    public void translateAnimation(double duration, Node node, double width){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(width);
        translateTransition.play();
    }

    int show = 0;


    @FXML
    void back(ActionEvent event) {
        if(show == 1){
            translateAnimation(0.5,pane2,829);
            show--;
            num.setText("1/3");
        } else if (show ==2) {
            translateAnimation(0.5,pane3,829);
            show--;
            num.setText("2/3");
        }
    }

    @FXML
    void next(ActionEvent event) {
        if(show == 0){
            translateAnimation(0.5,pane2,-829);
            show++;
            num.setText("2/3");
        } else if (show ==1) {
            translateAnimation(0.5,pane3,-829);
            show++;
            num.setText("3/3");
        }
    }

    private void initText(TextFlow textFlow, String path){
        try (InputStream inputStream = getClass().getResourceAsStream(path);
             Scanner scanner = new Scanner(inputStream)) {

            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }

            // Set the content to the Label
            Text text = new Text(content.toString());
            textFlow.getChildren().add(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}