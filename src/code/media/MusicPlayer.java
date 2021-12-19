package code.media;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {
    private String basePath;
    private String filename;
    private Player player;
    private BufferedInputStream buffer;
    private File[] files;

    {
        basePath = "media/music/";
        filename = "";
    }

    public MusicPlayer() {
        // Load music files
        try {
            files = gatMusic();
            filename = basePath + files[(int) (Math.random() * files.length)].getName();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void play() throws IOException, JavaLayerException {
            buffer = new BufferedInputStream(new FileInputStream(filename));
            player = new Player(buffer);
            player.play();
            if (player.isComplete()) {
                buffer.close();
                filename = files[(int) (Math.random() * files.length)].getName();
            }
    }

    private File[] gatMusic() throws FileNotFoundException {
        // Open music file
        File musicFile = new File(basePath);

        // Check if music file exists and is a directory
        if (!musicFile.exists() || !musicFile.isDirectory()) {
            throw new FileNotFoundException("Music file not found");
        }
        // Get list of files in music directory
        return musicFile.listFiles();
    }

    public void stop() throws IOException {
        buffer.close();
        player.close();
    }

    /*public static void main(String[] args) {
        MusicPlayer mp3 = new MusicPlayer("song.mp3");
        mp3.play();

    }*/

}
