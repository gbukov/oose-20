package name.panitz.game.framework.swing;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import name.panitz.game.framework.SoundTool;

public class JavaSoundTool implements SoundTool<AudioInputStream> {

  @Override
  public AudioInputStream loadSound(String fileName) {
    try {
      InputStream src = getClass().getClassLoader().getResourceAsStream(fileName);
      InputStream bufferedIn = new BufferedInputStream(src);
      // File laden
      return AudioSystem.getAudioInputStream(bufferedIn);
    } catch (UnsupportedAudioFileException |IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void playSound(AudioInputStream sound) {
    Clip clip;
    try {
      clip = AudioSystem.getClip();
      clip.open(sound);
      clip.start();
    } catch (LineUnavailableException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // TODO Erweiterung
  public static Clip playMusic(String fileName) {
    Clip clip = null;
    try {
      File f = new File(fileName);
      clip = AudioSystem.getClip();
      AudioInputStream ais = AudioSystem.getAudioInputStream(f);
      clip.open(ais);
      clip.start();
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
    return clip;
  }

  // TODO Erweiterung
  public static void stopMusic(Clip clip) {
    clip.stop();;
    clip.close();
  }

}
