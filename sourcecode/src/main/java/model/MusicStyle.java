package model;

public class MusicStyle {
    private final String name;
    public MusicStyle(String name) {
        this.name = name;
    }
    public String getPath() {
        return "/audio/musicstyles/" + name + "/";
    }

    public String getName() {
        return name;
    }
}
