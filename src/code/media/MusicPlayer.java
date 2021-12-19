package code.media;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;

public class MusicPlayer extends AudioPlayer {

    protected File[] files;

    // Constructor
    public MusicPlayer() {
        // Load music files
        try {
            files = getFiles(); // Get list of tracks
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void play() throws IOException, JavaLayerException {
        // Get random file
        filename = basePath + "/" + files[(int) (Math.random() * files.length)].getName();
        // Play file
        super.play(filename);
    }

    protected File[] getFiles() throws FileNotFoundException {
        // Open music file
        File musicFile = new File(basePath);

        // Check if music file exists and is a directory
        if (!musicFile.exists() || !musicFile.isDirectory()) {
            throw new FileNotFoundException("Audio folder not found");
        }
        // Get list of files in music directory
        return musicFile.listFiles();
    }
}
