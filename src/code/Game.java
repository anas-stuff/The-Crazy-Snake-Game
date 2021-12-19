package code;

import code.media.MusicPlayer;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

// This is a main class.
public class Game {
    // This is a main method.
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                new MusicPlayer().play();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();
        // Crete a game window.
        new Window();
    }
}
