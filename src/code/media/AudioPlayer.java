package code.media;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class AudioPlayer {
    protected String basePath;
    protected String filename;
    protected Player player;
    protected BufferedInputStream buffer;
    protected long position;
    protected boolean isPlaying;
    protected long length;

    {
        basePath = "media/music/"; // Initialize base path
        filename = ""; // Initialize filename
    }

    public void play(String filename) throws IOException, JavaLayerException {
        buffer = new BufferedInputStream(new FileInputStream(filename)); // Open file
        player = new Player(buffer); // Initialize player
        isPlaying = true; // Set isPlaying to true
        length = buffer.available(); // Get length of file
        player.play(); // Start player
        if (player.isComplete() && isPlaying) { // If player is complete
            length = 0; // Set length to 0
            buffer.close(); // Close buffer
            isPlaying = false; // Set isPlaying to false
        }
    }

    public void pause() throws IOException {
        if (isPlaying) { // If playing
            position = buffer.available(); // Save current position
            player.close(); // Stop player
            isPlaying = false; // Set isPlaying to false
        }
    }

    public void resume() throws IOException, JavaLayerException {
        if (!isPlaying) { // If not playing
            new Thread(() -> {
                try {
                    //code for resume button
                    buffer = new BufferedInputStream(new FileInputStream(filename)); // Re-open file
                    player = new Player(buffer); // Re-initialize player
                    length = buffer.available(); // Get length of file
                    buffer.skip(length - position); // Set position to saved position
                    player.play(); // Start player
                } catch (JavaLayerException | IOException e) {
                    e.printStackTrace();
                }
            }).start(); // Start thread
            isPlaying = true; // Set isPlaying to true
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public long getLength() {
        return length;
    }
}
