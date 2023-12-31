package model;

import interfaces.Player;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class PianoKey implements Player {
    private final String name;
    public PianoKey(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void play(MusicStyle musicStyle) {
        String path = musicStyle.getPath() + name + ".wav";
        play(path);
    }
    @Override
    public void play(String filepath) {
        Media sound = new Media(new File(filepath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    @Override
    public void stop() {
        System.out.println("Stopping");
    }
}
