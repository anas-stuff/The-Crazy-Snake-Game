package code;

import code.media.MusicPlayer;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

// This is a main class.
public class Game {
    public static final MusicPlayer musicPlayer = new MusicPlayer();

    // Constructor.
    public Game() {
        new Thread(() -> {
            while (true) {
                try {
                    musicPlayer.play();
                    Thread.sleep(musicPlayer.getLength());
                } catch (IOException | InterruptedException | JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // Create a game window.
        new Window();
    }

    // This is a main method.
    public static void main(String[] args) {
        new Game();
    }
}