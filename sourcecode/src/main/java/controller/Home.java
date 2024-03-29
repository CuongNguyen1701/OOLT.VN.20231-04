package controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.piano.MusicStyle;
import model.piano.Piano;
import model.piano.PianoKey;
import model.record.Recorder;
import model.piano.Setting;
import model.record.StepRecord;
import model.record.Record;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class Home {
    @FXML private Pane pianoPane;
    @FXML private Slider volumeSlider;
    @FXML private Label labelImportRecord;
    @FXML private Button buttonPlayRecord;
    @FXML private VBox vboxMusicStyle;
    @FXML private ToggleButton buttonKeyHint;
    @FXML private AnchorPane keyHintParent;

    private Scene scene;
    Piano piano;
    Setting setting;
    Recorder recorder;
    Record record;
    // key fx:id to key name
    Map<String, String> idToKeyName = new HashMap<>();
    // keyboard's key code to fx:id
    Map<Integer, String> keyCodeToId = new HashMap<>();
    @FXML
    private void initialize() {

        // create piano
        ArrayList<String> pianoKeyNames = getAllPianoKeyName();
        this.setting = new Setting();
        this.piano = new Piano(pianoKeyNames);
        initializePianoKeyMapping();
        piano.batchUpdateLastUsedPath(setting.getMusicStyle().getPath());
        initializePianoKeyBinding();
        this.recorder = new Recorder();
        initializeMusicStyleToggleButtons();
        // volume indicator
        volumeSlider.setValue(setting.getVolume());
        setVolumeSliderFill(setting.getVolume());
        volumeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            double percentage = 100.0 * newValue.doubleValue() / volumeSlider.getMax();
            setting.updateVolume(newValue.intValue());
            setVolumeSliderFill(percentage);
        });

        buttonKeyHint.setText("KeyHint:Off");
        keyHintParent.setVisible(false);
        keyHintParent.setPickOnBounds(false);
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
    private void initializeMusicStyleToggleButtons() {
        ToggleGroup musicStyleToggleGroup = new ToggleGroup();
        ArrayList<RadioButton> musicStyleButtons = new ArrayList<>();
        boolean first = true;
        for (String musicStyleName : Setting.VALID_MUSIC_STYLES) {
            // capitalize the first letter of the music style name before displaying it
            String musicStyleNameCapitalizedFirst = musicStyleName.substring(0, 1).toUpperCase() + musicStyleName.substring(1);
            RadioButton musicStyleButton = new RadioButton(musicStyleNameCapitalizedFirst);
            musicStyleButton.setToggleGroup(musicStyleToggleGroup);

            // set the first music style as the default selected music style
            musicStyleButton.setSelected(first);
            musicStyleButton.setOnAction(event -> {
                setting.setMusicStyle(new MusicStyle(musicStyleName));
                // initialize the piano with the new music style
                piano.batchUpdateLastUsedPath(setting.getMusicStyle().getPath());
            });
            first = false;
            musicStyleButtons.add(musicStyleButton);
            vboxMusicStyle.getChildren().add(musicStyleButton);
        }
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
    private void initializePianoKeyBinding() {
        for (String pianoKeyId : idToKeyName.keySet()) {
            String keyName = idToKeyName.get(pianoKeyId);
            PianoKey pianoKey = piano.getKey(keyName);
            // Register a callback for highlighting and recording
            pianoKey.addPlayCallback(() -> {
                highlightKey(pianoKeyId);
                recorder.recordKeyPlayed(piano.getKey(keyName));
            });
        }
    }


    @FXML void handleKeyTyped(KeyEvent event){
        int keyValue = event.getCharacter().charAt(0);
        piano.playKey(keyValue, setting);
    }
    @FXML
    void handlePianoKeyClick(ActionEvent event) {
        String id = ((Button) event.getSource()).getId();
        String keyName = idToKeyName.get(id);
        piano.playKey(keyName, setting);
    }
    //highlight the key when it is played
    void highlightKey(String keyFxId) {
        String style = "-fx-background-color: #ee1111;";

        try {
            Node keyNode = pianoPane.lookup("#" + keyFxId);

            if (keyNode != null) {
                keyNode.setStyle(style);

                // Schedule the removal of style after 0.1 second
                PauseTransition pause = new PauseTransition(Duration.millis(100));
                pause.setOnFinished(event -> keyNode.setStyle(""));
                pause.play();
            } else {
                System.out.println("Key not found");
            }
        } catch (NullPointerException e) {
            System.out.println("Error highlighting key: " + e.getMessage());
        }
    }


    @FXML
    private void showAboutPopup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/About.fxml"));
            AnchorPane aboutPane = fxmlLoader.load();
            Scene scene = new Scene(aboutPane);
            Stage stage = new Stage();
            stage.setTitle("About");
            stage.setScene(scene);
            stage.getIcons().add(new Image("/images/about/icon.png"));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showHelpPopup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Help.fxml"));
            AnchorPane aboutPane = fxmlLoader.load();
            Scene scene = new Scene(aboutPane);
            Stage stage = new Stage();
            stage.setTitle("Help");
            stage.getIcons().add(new Image("/images/help_menu/icon.png"));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleButtonRecordToggle(ActionEvent actionEvent) {
        if(recorder.getRecordingStatus()){
            recorder.stopRecording();
            ((Button)actionEvent.getSource()).setText("Start Recording");
            showExportPopup();
        }
        else{
            recorder.startRecording();
            ((Button)actionEvent.getSource()).setText("Stop Recording");
        }
    }
    @FXML
    private void showExportPopup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Export.fxml"));
            AnchorPane exportPane = fxmlLoader.load();
            Export export = fxmlLoader.getController();
            export.setRecord(recorder.getRecord());
            Scene scene = new Scene(exportPane);
            Stage stage = new Stage();
            stage.setTitle("Export");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleImportRecord(ActionEvent actionEvent) {
        FileChooser.ExtensionFilter extFilter;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Destination");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("WAV files (*.wav)", "*.wav"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile == null)
            return;
        String filePath = selectedFile.getAbsolutePath();
        //return if selected file is a directory
        if(selectedFile.isDirectory())
            return;
        record = new StepRecord();
        try {
            record.importRecord(filePath, piano);
        } catch (Exception e) {
            e.printStackTrace();
        }
        labelImportRecord.setText(selectedFile.getName());
        labelImportRecord.setStyle("-fx-text-fill: #000000;");
        buttonPlayRecord.setVisible(true);
        buttonPlayRecord.setDisable(false);
    }

    public void handleButtonPlayRecord(ActionEvent actionEvent) {
        if(record == null)
            return;
        record.play();
    }
    boolean keyHintOn = false;

    @FXML
    void handleButtonKeyHintOnClick(ActionEvent actionEvent){
        keyHintOn = !keyHintOn;
        keyHintParent.setVisible(keyHintOn);

        if(keyHintOn)
            buttonKeyHint.setText("KeyHint:On");
        else
            buttonKeyHint.setText("KeyHint:Off");
    }
}
