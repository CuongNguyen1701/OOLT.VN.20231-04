package model.record;

import model.PianoKey;

import java.util.ArrayList;

public class Recorder {
    ArrayList<PianoEvent> stepRecordBuffer = new ArrayList<>();
    private boolean isRecording = false;
    private long lastKeyPlayedTime;

    public boolean getRecordingStatus() {
        return isRecording;
    }

    public void startRecording(){
        stepRecordBuffer.clear();
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
        stepRecordBuffer.add(new PianoEvent(pianoKey, interval));
        lastKeyPlayedTime = currentTime;
    }
    public void stopRecording(){
        isRecording = false;
        System.out.println(new StepRecord(stepRecordBuffer));
        System.out.println("Recording stopped.");
    }
    public StepRecord getStepRecord(){
        if (stepRecordBuffer == null)
            return null;
        return new StepRecord(stepRecordBuffer);
    }

}
