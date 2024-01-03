package model.record;

import interfaces.Player;

import java.nio.file.Paths;

public abstract class Record implements Player {
    protected boolean isPlaying = false;
    protected String path = Paths.get("target","classes", "output", "records").toString();
    public Record() {

    }
    public void play(){}
    public abstract void export(String path);
    public abstract void importRecord(String path) throws Exception;
}
