package model;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        Setting setting = new Setting();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        long delay = 0; // Initial delay

        for (PianoEvent pianoEvent : pianoEvents) {
            scheduler.schedule(() -> {
                pianoEvent.getKeyPressed().play(setting);
                System.out.println(pianoEvent);
            }, delay, TimeUnit.MILLISECONDS);

            // Increment the delay for the next task
            delay += pianoEvent.getInterval();
        }

        // Shutdown the scheduler after the last task
        scheduler.shutdown();
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (PianoEvent pianoEvent : pianoEvents) {
            result.append(pianoEvent.toString());
            result.append("\n");
        }
        return result.toString();
    }
    @Override public void stop() {
        System.out.println("Stopping");
    }
}
