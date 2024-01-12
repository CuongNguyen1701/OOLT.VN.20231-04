package model.record;

import model.piano.PianoKey;

public class PianoEvent {
    private final PianoKey KEYPRESSED;
    private final int INTERVAL;
    public PianoEvent(PianoKey keyPressed, int interval) {
        this.KEYPRESSED = keyPressed;
        this.INTERVAL = interval;
    }
    public PianoKey getKeyPressed() {
        return KEYPRESSED;
    }
    public int getInterval() {
        return INTERVAL;
    }
    @Override
    public String toString() {
        return KEYPRESSED.getName() + ": " + INTERVAL + "ms";
    }
}
