package model;

import interfaces.Player;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PianoKey implements Player {
    private final String name;
    public PianoKey(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void play(Setting setting) {
        String path = setting.getMusicStyle().getPath() + name + ".wav";
        double volume = setting.getVolume() / 100.0;
        play(path, volume);
    }
    @Override
    public void play(String filepath, double volume) {
        String soundPath = Paths.get("target","classes", filepath).toString();
        Media sound = new Media(new File(soundPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }
    @Override
    public void stop() {
        System.out.println("Stopping");
    }
}
