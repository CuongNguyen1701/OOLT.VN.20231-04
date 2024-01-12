package model.piano;

public class MusicStyle {
    private final String NAME;
    public MusicStyle(String name) {
        this.NAME = name;
    }
    public String getPath() {
        return "/audio/musicstyles/" + NAME + "/";
    }

    public String getName() {
        return NAME;
    }
}
