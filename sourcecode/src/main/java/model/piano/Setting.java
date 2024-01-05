package model.piano;

import java.util.ArrayList;

public class Setting {
    public static final ArrayList<String> VALID_MUSIC_STYLES = new ArrayList<String>() {{
        add("acoustic");
        add("organs");
        add("koto");
    }};
    private int volume = 50;


    MusicStyle musicStyle = new MusicStyle("acoustic");
    public Setting(){}
    public Setting(int volume){
        this.volume = volume;
    }
    public Setting(MusicStyle musicStyle) {
        this.musicStyle = musicStyle;
    }
    public Setting(int volume, MusicStyle musicStyle) {
        this.volume = volume;
        this.musicStyle = musicStyle;
    }
    public int getVolume(){
        return volume;
    }
    public void updateVolume(int volume){
        if(volume < 0)
            return;
        if(volume > 100)
            return;
        this.volume = volume;
    }
    public MusicStyle getMusicStyle(){
        return musicStyle;
    }
    public void setMusicStyle(MusicStyle musicStyle){
        if(musicStyle == null)
            return;
        if(!VALID_MUSIC_STYLES.contains(musicStyle.getName()))
            return;
        this.musicStyle = musicStyle;
    }
}
