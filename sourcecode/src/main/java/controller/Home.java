package controller;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.MusicStyle;
import model.Piano;
import model.Setting;

import java.io.File;
import java.io.PipedInputStream;
import java.nio.file.Paths;
import java.util.*;

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
    private Scene scene;
    Piano piano;
    Setting setting;
    // key fx:id to key name
    Map<String, String> idToKeyName = new HashMap<>();
    // keyboard's key code to fx:id
    Map<Integer, String> keyCodeToId = new HashMap<>();
    @FXML
    private void initialize() {
        // create piano
        ArrayList<String> pianoKeyNames = getAllPianoKeyName();
        this.piano = new Piano(pianoKeyNames);
        initializePianoKeyMapping();
        this.setting = new Setting();
        // volume indicator
        volumeSlider.setValue(setting.getVolume());
        setVolumeSliderFill(setting.getVolume());
        volumeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            double percentage = 100.0 * newValue.doubleValue() / volumeSlider.getMax();
            setting.updateVolume(newValue.intValue());
            setVolumeSliderFill(percentage);
        });
    }
    void setVolumeSliderFill(double percentage){
        String style = String.format(
                "-track-color: linear-gradient(to top, " +
                        "-fx-accent 0%%, " +
                        "-fx-accent %1$.1f%%, " +
                        "-default-track-color %1$.1f%%, " +
                        "-default-track-color 100%%);",
                percentage);
        volumeSlider.setStyle(style);
    }
    private ArrayList<String> getAllPianoKeyName() {
        String prefix = "key";
        ArrayList<String> pianoKeyNames = new ArrayList<>();
        ArrayList<String> majorKeyNames = new ArrayList<>();
        ArrayList<String> minorKeyNames = new ArrayList<>();
        String audioPath = Paths.get("target","classes", "audio", "musicstyles", "acoustic").toString();
        File folder = new File(audioPath);
        File[] files = folder.listFiles();
        // get all file names from the directory
        assert files != null;
        for (File file : files) {
            if (file == null || file.isDirectory() || file.isHidden()) {
                continue;
            }
            String filepath = file.toString();
            String filename = filepath.substring(filepath.lastIndexOf("\\") + 1);
            // remove .wav extension
            filename = filename.substring(0, filename.length() - 4);
            if (filename.contains("s")) {
                minorKeyNames.add(filename);
            } else {
                majorKeyNames.add(filename);
            }
        }
        for (Node child : pianoPane.getChildren()) {
            if (child instanceof Button pianoKey) {
                String id = pianoKey.getId();
                if (id != null && id.startsWith(prefix)) {
                    // get the numeric value of the key
                    String numericPart = id.substring(prefix.length());
                    int keyNumber = Integer.parseInt(numericPart);

                    // add the key name to the list based on the key number
                    // key number 1-32 is major key(white key)
                    // key number 33-55 is minor key(black key)
                    if(keyNumber < 33){
                        pianoKeyNames.add(majorKeyNames.get(keyNumber - 1));
                        idToKeyName.put(id, majorKeyNames.get(keyNumber - 1));
                    }
                    else{
                        pianoKeyNames.add(minorKeyNames.get(keyNumber - 33));
                        idToKeyName.put(id, minorKeyNames.get(keyNumber - 33));
                    }
                }
            }
        }
        return pianoKeyNames;
    }
    private void initializePianoKeyMapping(){
        // no shift key holding to play major notes
        char[] keyNamesMajorNotes = "qwertyuiop[]asdfghjkl;'zxcvbnm,.".toCharArray(); // 32 keys
        // shift key holding to play minor notes
        char[] keyNamesMinorNotes = "QWERTYUIOP{}ASDFGHJKL:\"".toCharArray(); // 23 keys

        for(int i = 0; i < (keyNamesMajorNotes.length + keyNamesMinorNotes.length); i++){
            String keyIdPrefix = "key0";
            int keyId = i + 1;
            keyIdPrefix += ((keyId<10) ? "0" : "");//add extra 0 to make sure it is 3 digits
            if(i < keyNamesMajorNotes.length){
                piano.setKeyMap(keyNamesMajorNotes[i], idToKeyName.get(keyIdPrefix + keyId));
                keyCodeToId.put((int)keyNamesMajorNotes[i], keyIdPrefix + keyId);
                continue;
            }
            piano.setKeyMap(keyNamesMinorNotes[i - keyNamesMajorNotes.length], idToKeyName.get(keyIdPrefix + keyId));
            keyCodeToId.put((int)keyNamesMinorNotes[i - keyNamesMajorNotes.length], keyIdPrefix + keyId);
        }
    }
    @FXML void handleKeyTyped(KeyEvent event){
        int keyValue = event.getCharacter().charAt(0);
        highlightKey(keyCodeToId.get(keyValue));
        piano.playKey(keyValue, setting);
    }
    @FXML
    void handlePianoKeyClick(ActionEvent event) {
        String id = ((Button) event.getSource()).getId();
        highlightKey(id);
        String keyName = idToKeyName.get(id);
        piano.playKey(keyName, setting);
    }
    //highlight the key when it is played
    void highlightKey(String keyFxId){
        String style = "-fx-background-color: #ee1111;";
        pianoPane.lookup("#" + keyFxId).setStyle(style);
        // remove the style after 0.1 second
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        pianoPane.lookup("#" + keyFxId).setStyle("");
                    }
                },
                100
        );
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
