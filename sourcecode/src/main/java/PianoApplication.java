import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.stage.WindowEvent;

public class PianoApplication {
    public static class AppUI extends Application {
        @Override
        public void start(javafx.stage.Stage primaryStage) throws Exception {
            final String HOME_FXML_FILE_PATH = "/view/Home.fxml";
            System.out.println(getClass().getResource(HOME_FXML_FILE_PATH));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(HOME_FXML_FILE_PATH));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);

            primaryStage.setTitle("Piano");
            primaryStage.setResizable(false);
            primaryStage.setFullScreen(false);
            primaryStage.setFullScreenExitHint("");
            primaryStage.getIcons().add(new Image("/images/icon.png"));

            primaryStage.setOnCloseRequest((WindowEvent event) -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Exit Confirmation");
                alert.setHeaderText("Are you sure you want to exit the application?");
                //alert.setContentText("Are you sure you want to exit the application?");

                if (alert.showAndWait().get() == ButtonType.OK)
                    primaryStage.close();
                else
                    event.consume();
            });
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        public static void main(String[] args) {
            try{
                launch(args);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        AppUI.main(args);
    }
}
