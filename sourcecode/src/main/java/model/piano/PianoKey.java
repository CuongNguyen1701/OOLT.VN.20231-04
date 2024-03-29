package model.piano;

import interfaces.Player;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PianoKey implements Player {
    private final String name;
    private MediaPlayer mediaPlayer;
    private String lastUsedPath;

    private final List<Runnable> PLAY_CALLBACKS = new ArrayList<>();
    // Method to register callback when the key is played
    public void addPlayCallback(Runnable callback) {
        PLAY_CALLBACKS.add(callback);
    }
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
            lastUsedPath = stylePath;
            reloadMediaPlayer();
        }
        mediaPlayer.setVolume(volume);
        play();
    }
    void updateLastUsedPath(String newPath) {
        lastUsedPath = newPath;
        reloadMediaPlayer();
    }
    private void reloadMediaPlayer() {
        String path = lastUsedPath + name + ".wav";
        String soundPath = Paths.get("target","classes", path).toString();
        Media sound = new Media(new File(soundPath).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
    }
    @Override
    public void play() {
        PLAY_CALLBACKS.forEach(Runnable::run);
        mediaPlayer.play();
        mediaPlayer.seek(mediaPlayer.getStartTime());
    }
    @Override
    public void stop() {
        System.out.println("Stopping");
    }
}
