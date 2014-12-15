package game.hirois.hiroi2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundBox {
	private ArrayList<Clip> clips = new ArrayList<Clip>();

	public int loadSound(File file) throws UnsupportedAudioFileException,
			IOException, LineUnavailableException
	{
		AudioInputStream aistream = AudioSystem.getAudioInputStream(file);
		DataLine.Info info = new DataLine.Info(Clip.class, aistream.getFormat());
		Clip clip = (Clip)AudioSystem.getLine(info);
		clip.open(aistream);
		this.clips.add(clip);
		return this.clips.size()-1;
	}

	//再生のためにクリップを取得する
	public Clip getClip(int idx){
		if(idx >= this.clips.size()){
			return null;
		}
		return this.clips.get(idx);
	}

	//簡易再生
	public Clip playOneSHot(int idx){
		Clip clip = this.getClip(idx);
		clip.setFramePosition(0);
		clip.start();
		return clip;
	}

	public static SoundBox singleton = new SoundBox();
}
