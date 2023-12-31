package model;

import java.util.HashMap;
import java.util.Map;

public class Piano {
    // for keyboard
    private final Map<Integer, PianoKey> keyMap;
    // for mouse clicking
    private final Map<String, PianoKey> nameMap = new HashMap<>();
    public Piano(Map<Integer, PianoKey> keyMap) {
        this.keyMap = keyMap;
        for (PianoKey pianoKey : keyMap.values()) {
            nameMap.put(pianoKey.getName(), pianoKey);
        }
    }
    public Piano(PianoKey[] pianoKeys) {
        this.keyMap = new HashMap<>();
        for (PianoKey pianoKey : pianoKeys) {
            nameMap.put(pianoKey.getName(), pianoKey);
        }
    }
    public void setKeyMap(int keyCode, PianoKey pianoKey) {
        keyMap.put(keyCode, pianoKey);
        nameMap.put(pianoKey.getName(), pianoKey);
    }
    public void playKey(String keyName, MusicStyle musicStyle){
        PianoKey pianoKey = nameMap.get(keyName);
        if (pianoKey != null) {
            pianoKey.play(musicStyle);
        }
    }
    public void playKey(int keyCode, MusicStyle musicStyle) {
        PianoKey pianoKey = keyMap.get(keyCode);
        if (pianoKey != null) {
            pianoKey.play(musicStyle);
        }
    }

}
