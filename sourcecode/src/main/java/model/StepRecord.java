package model;

import java.util.ArrayList;

public class StepRecord extends Record {
    ArrayList<PianoEvent> pianoEvents;
    public StepRecord(ArrayList<PianoEvent> pianoEvents) {
        super();
        this.pianoEvents = pianoEvents;
    }
    public void play(){
        play(path, 1);
    }
    @Override public void play(String filepath, double volume) {
        for(PianoEvent pianoEvent : pianoEvents) {
            //wait for the interval before playing the next key
            try {
                Thread.sleep(pianoEvent.getInterval());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pianoEvent.getKeyPressed().play(new Setting());
        }
    }
    @Override public void stop() {
        System.out.println("Stopping");
    }
}
