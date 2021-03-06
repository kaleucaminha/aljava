package aljava.midia;

import java.io.*;
import javax.sound.sampled.*;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke Som.SOUND_NAME.play().
 * 3. You might optionally invoke the static method Som.init() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable Som.volume to mute the sound.
 */
public class Som {
   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;

   // Constructor to construct each element of the enum with its own sound file.
   public Som(String soundFileName) {
      try {
         // Use URL (instead of File) to read from disk and JAR.
         File url = new File(soundFileName);
         // Set up an audio input stream piped from the sound file.
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         // Get a clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   // Play or Re-play the sound effect from the beginning, by rewinding.
   public void toca() {
     if (clip.isRunning())
        clip.stop();   // Stop the player if it is still running
     clip.start();     // Start playing
   }

   public void loop() {
     if (clip.isRunning())
        clip.stop();   // Stop the player if it is still running
     clip.setFramePosition(0); // rewind to the beginning
     clip.loop(Clip.LOOP_CONTINUOUSLY);    // Start playing
   }

   public void pausa() {
     if (clip.isRunning())
        clip.stop();   // Stop the player if it is still running
   }

   public void reinicia(){
       clip.setFramePosition(0);
   }
}