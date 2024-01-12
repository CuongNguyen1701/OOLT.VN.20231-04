package model.piano;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Piano {
    // for keyboard
    private final Map<Integer, PianoKey> KEYMAP;
    // for mouse clicking
    private final Map<String, PianoKey> NAMEMAP = new HashMap<>();
    public Piano(Map<Integer, PianoKey> KEYMAP) {
        this.KEYMAP = KEYMAP;
        for (PianoKey pianoKey : KEYMAP.values()) {
            NAMEMAP.put(pianoKey.getName(), pianoKey);
        }
    }
    public Piano(ArrayList<String> keyNames) {
        this.KEYMAP = new HashMap<>();
        for (String keyName : keyNames) {
            PianoKey pianoKey = new PianoKey(keyName);
            NAMEMAP.put(keyName, pianoKey);
        }
    }
    public Piano(PianoKey[] pianoKeys) {
        this.KEYMAP = new HashMap<>();
        for (PianoKey pianoKey : pianoKeys) {
            NAMEMAP.put(pianoKey.getName(), pianoKey);
        }
    }
    public void setKeyMap(int keyCode, String pianoKeyName) {
        KEYMAP.put(keyCode, NAMEMAP.get(pianoKeyName));
    }
    public PianoKey getKey(String keyName){
        return NAMEMAP.get(keyName);
    }
    public PianoKey getKey(int keyCode){
        return KEYMAP.get(keyCode);
    }
    public void playKey(String keyName, Setting setting) {
        PianoKey pianoKey = NAMEMAP.get(keyName);
        if (pianoKey != null) {
            pianoKey.play(setting);
        }
    }
    public void playKey(int keyCode, Setting setting) {
        PianoKey pianoKey = KEYMAP.get(keyCode);
        if (pianoKey != null) {
            pianoKey.play(setting);
        }
    }
    public void batchUpdateLastUsedPath(String path) {
        for (PianoKey pianoKey : KEYMAP.values()) {
            pianoKey.updateLastUsedPath(path);
        }
    }

}
