package model;

import java.util.ArrayList;
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
    public Piano(ArrayList<String> keyNames) {
        this.keyMap = new HashMap<>();
        for (String keyName : keyNames) {
            PianoKey pianoKey = new PianoKey(keyName);
            nameMap.put(keyName, pianoKey);
        }
    }
    public Piano(PianoKey[] pianoKeys) {
        this.keyMap = new HashMap<>();
        for (PianoKey pianoKey : pianoKeys) {
            nameMap.put(pianoKey.getName(), pianoKey);
        }
    }
    public void setKeyMap(int keyCode, String pianoKeyName) {
        keyMap.put(keyCode, nameMap.get(pianoKeyName));
    }
    public void playKey(String keyName, Setting setting) {
        PianoKey pianoKey = nameMap.get(keyName);
        if (pianoKey != null) {
            pianoKey.play(setting);
        }
    }
    public void playKey(int keyCode, Setting setting) {
        PianoKey pianoKey = keyMap.get(keyCode);
        if (pianoKey != null) {
            pianoKey.play(setting);
        }
    }

}
