package view;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Ost {
	
private static Ost Singleton = new Ost();
	
	private Ost() {}
	
	public static Ost getInstance() {
		return Singleton;
	}
	
	private Clip bgm;
	private Clip sfx;
	AudioInputStream audioInputStream;
	
	public File background = new File("Background.wav");
	public File Intro = new File("Selection.wav");
	public File Win = new File("Win.wav");
	public File pawnhit = new File("Pawn_Hit.wav");
	public File pawnact = new File("Pawn_Act.wav");
	public File pawnpas = new File("Pawn_Pas.wav");
	public File rookhit = new File("Rook_Hit.wav");
	public File rookact = new File("Rook_Act.wav");
	public File rookpas = new File("Rook_Change.wav");
	public File kinghit = new File("King_Hit.wav");
	public File move = new File("Move.wav");
	
	
	
	public void playMusic(File Sound) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(Sound);
			bgm = AudioSystem.getClip();
			bgm.open(audioInputStream);
			bgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception e) {
		}
	}
	
	public void playSfx(File Sound) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
			sfx = AudioSystem.getClip();
			sfx.open(audioInputStream);
			sfx.start();
			
		}
		catch(Exception e) {
		}
	}
	
	public void stopMusic() {
		bgm.stop();
	}
	
	
	
	

}
