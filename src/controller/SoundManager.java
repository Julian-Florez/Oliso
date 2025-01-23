package controller;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The SoundManager class provides methods to play correct and wrong sounds.
 */
public abstract class SoundManager {

    /**
     * Plays the sound for a correct answer.
     */
    static public void playCorrectSound() {
        playSound("assets/audio/correct.wav");
    }

    /**
     * Plays the sound for a wrong answer.
     */
    static public void playWrongSound() {
        playSound("assets/audio/wrong.wav");
    }

    /**
     * Generic method to play a sound from the resources folder.
     */
    private static void playSound(String resourcePath) {
        try (InputStream is = SoundManager.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Sound file not found: " + resourcePath);
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
