package model.record;

import model.piano.PianoKey;

public class PianoEvent {
    private final PianoKey KEY_PRESSED;
    private final int INTERVAL;
    public PianoEvent(PianoKey keyPressed, int interval) {
        this.KEY_PRESSED = keyPressed;
        this.INTERVAL = interval;
    }
    public PianoKey getKeyPressed() {
        return KEY_PRESSED;
    }
    public int getInterval() {
        return INTERVAL;
    }
    @Override
    public String toString() {
        return KEY_PRESSED.getName() + ": " + INTERVAL + "ms";
    }
}
