import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import controller.Home;
import javafx.scene.Parent;
import javafx.scene.Scene;
public class PianoApplication {
    public static class AppUI extends Application {
        @Override
        public void start(javafx.stage.Stage primaryStage) throws Exception {
            final String HOME_FXML_FILE_PATH = "/fxml/Home.fxml";
            System.out.println(getClass().getResource(HOME_FXML_FILE_PATH));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(HOME_FXML_FILE_PATH));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Piano");
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
