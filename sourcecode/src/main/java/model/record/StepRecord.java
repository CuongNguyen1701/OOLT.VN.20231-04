package model.record;

import model.piano.Piano;
import model.piano.Setting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StepRecord extends Record {
    ArrayList<PianoEvent> pianoEvents;
    ScheduledExecutorService scheduler;
    public StepRecord() {
        super();
        pianoEvents = new ArrayList<>();
    }
    public StepRecord(ArrayList<PianoEvent> pianoEvents) {
        super();
        this.pianoEvents = pianoEvents;
    }
    @Override public void play() {
        if(isPlaying)
            return;
        Setting setting = new Setting();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        long delay = 0; // Initial delay
        isPlaying = true;
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
        isPlaying = false;
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
        if(!isPlaying)
            return;
        scheduler.shutdownNow();
    }
    @Override public void export(String path) {
        //export to csv file
        List<String[]> dataLines = new ArrayList<>();
        for (PianoEvent pianoEvent : pianoEvents) {
            String[] data = {pianoEvent.getKeyPressed().getName(), String.valueOf(pianoEvent.getInterval())};
            dataLines.add(data);
        }
        File csvFile = new File(path);
        try(PrintWriter printWriter = new PrintWriter(csvFile)){
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(printWriter::println);
        }catch (Exception e){
            e.printStackTrace();
        }
        assert (csvFile.exists());

    }
    private String convertToCSV(String[] data) {
        return String.join(",", data);
    }
    @Override public void importRecord(String path, Piano piano) throws Exception {
        // read from csv file
        List<String[]> dataLines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                dataLines.add(data);
            }
        }
        // convert to piano events
        pianoEvents = new ArrayList<>();
        for (String[] dataLine : dataLines) {
            String pianoKeyName = dataLine[0];
            int interval = Integer.parseInt(dataLine[1]);
            pianoEvents.add(new PianoEvent(piano.getKey(pianoKeyName), interval));
        }
    }
}
