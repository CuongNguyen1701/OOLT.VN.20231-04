package model.record;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.piano.Piano;

import java.io.File;

public class AudioRecord extends Record {
    private final MediaPlayer mediaPlayer;
    public AudioRecord(String filename) {
        this.path = super.path + "\\audio_records\\" + filename + ".wav";
        Media sound = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
    }
    @Override
    public void play() {
        mediaPlayer.play();
    }
    @Override
    public void stop() {
        mediaPlayer.stop();
    }
    @Override
    public void export(String path) {
        //TODO: export to wav
    }
    @Override
    public void importRecord(String path, Piano piano) throws Exception {
        //TODO: import from wav
    }
}
