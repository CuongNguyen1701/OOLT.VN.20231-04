package model;

public class PianoEvent {
    private PianoKey keyPressed;
    private int interval;
    public PianoEvent(PianoKey keyPressed, int interval) {
        this.keyPressed = keyPressed;
        this.interval = interval;
    }
    public PianoKey getKeyPressed() {
        return keyPressed;
    }
    public int getInterval() {
        return interval;
    }
}
