package model.record;

import model.piano.PianoKey;

import java.util.ArrayList;

public class Recorder {
    private final ArrayList<PianoEvent> STEP_RECORD_BUFFER = new ArrayList<>();
    private boolean isRecording = false;
    private long lastKeyPlayedTime;

    private Record record;

    public boolean getRecordingStatus() {
        return isRecording;
    }

    public void startRecording(){
        STEP_RECORD_BUFFER.clear();
        lastKeyPlayedTime = System.currentTimeMillis();
        isRecording = true;
        System.out.println("Recording...");
    }
    public void recordKeyPlayed(PianoKey pianoKey){
        if(!isRecording)
            return;
        if(pianoKey == null)
            return;
        // get the interval between the last key played and the current key played
        long currentTime = System.currentTimeMillis();
        int interval = (int)(currentTime - lastKeyPlayedTime);
        // add the key and interval to the buffer
        STEP_RECORD_BUFFER.add(new PianoEvent(pianoKey, interval));
        lastKeyPlayedTime = currentTime;
    }
    public void stopRecording(){
        isRecording = false;
        System.out.println("Recording stopped.");
        record = new StepRecord(STEP_RECORD_BUFFER);
    }
    public Record getRecord(){
        return record;
    }
}
