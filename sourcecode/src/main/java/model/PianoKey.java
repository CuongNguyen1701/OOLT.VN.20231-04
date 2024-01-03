package model;

import interfaces.Player;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PianoKey implements Player {
    private final String name;
    private MediaPlayer mediaPlayer;
    private String lastUsedPath;

    public PianoKey(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void play(Setting setting) {
        String stylePath = setting.getMusicStyle().getPath();
        double volume = setting.getVolume() / 100.0;
        // only create a new MediaPlayer if the music style is different from the last used path
        if (!stylePath.equals(lastUsedPath)) {
            lastUsedPath = stylePath;String path = stylePath + name + ".wav";
            String soundPath = Paths.get("target","classes", path).toString();
            Media sound = new Media(new File(soundPath).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
        }
        mediaPlayer.setVolume(volume);
        play();
    }
    void updateLastUsedPath(String path) {
        lastUsedPath = path;
    }
    @Override
    public void play() {
        mediaPlayer.play();
        mediaPlayer.seek(mediaPlayer.getStartTime());
    }
    @Override
    public void stop() {
        System.out.println("Stopping");
    }
}
