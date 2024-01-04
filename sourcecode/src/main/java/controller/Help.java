package controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import  javafx.util.Duration;
import javafx.scene.text.TextFlow;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Help {
    @FXML
    private Label num;

    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonNext;

    @FXML
    private List<AnchorPane> panes = new ArrayList<>();

    @FXML
    private List<TextFlow> textFlows = new ArrayList<>();

    @FXML
    private AnchorPane root;

    @FXML
    private void initialize(){
        for(Node child: root.getChildren()){
            if(child instanceof AnchorPane pane){
                panes.add(pane);
            }
        }

        for (int i = 0; i < panes.size();i++){
            String path = "../texts/help_menu/" + (i+1) + ".txt";
            if(panes.get(i).getChildren().get(2) instanceof TextFlow textFlow)
                initText(textFlow,path);
        }

        buttonBack.setDisable(true);
        buttonNext.setDisable(false);
    }

    public void translateAnimation(double duration, Node node, double width, boolean isNext){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(width);
        translateTransition.play();
    }

    int show = 0;


    @FXML
    void back(ActionEvent event) {
        buttonNext.setDisable(false);
        if(show <= 0)   return;
        translateAnimation(0.5,panes.get(show),1000, false);
        show--;
        num.setText(show + 1 +"/6");
        buttonBack.setDisable(show<=0);
    }

    @FXML
    void next(ActionEvent event) {
        buttonBack.setDisable(false);
        if(show >= 5)   return;
        translateAnimation(0.5,panes.get(show+1),-1000,true);
        show++;
        num.setText(show+1+"/6");
        buttonNext.setDisable(show >=5);
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