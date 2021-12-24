package com.anas.code.media;

import javazoom.jl.decoder.JavaLayerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayer extends AudioPlayer {

    {
        basePath += "/music/"; // Add music to the base path
    }

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
        filename = basePath + files[(int) (Math.random() * files.length)].getName();
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
