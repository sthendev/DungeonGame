package unsw.dungeon;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
	private String filename;

	public SoundPlayer() {
		super();
		this.filename = null;
	}
	
	
	
	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public void playSound(String filename) {
    	AudioInputStream audioInputStream = null;
    	String name = "sounds/" + filename;
    	setFilename(name);
		try {
			audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(this.filename));
		} catch (UnsupportedAudioFileException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
          
        Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} 
          
        // open audioInputStream to the clip 
        try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        clip.start();
    }
	
}
