package code.media;

import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class SfxPlayer extends AudioPlayer {

    {
        basePath += "/sfx/"; // Add sfx to base path
    }

    public SfxPlayer() {
        super();
    }

    public void play(SFX sfx) {
        // Play sound effect on a new thread
        new Thread(() -> {
            try {
                super.play(basePath + sfx.getFileName());
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
