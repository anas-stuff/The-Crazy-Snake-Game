package com.anas.code.controllers;

import com.anas.code.Game;
import com.anas.code.GamePanel;
import com.anas.code.enums.Directions;
import com.anas.code.gameComponents.Snake;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyInput extends KeyAdapter {
    // Variables
    private final Snake snake;
    private final GamePanel game;

    // Constructor
    public KeyInput(Snake snake, GamePanel game) {
        this.snake = snake;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed " + e.getKeyChar());
        int key = e.getKeyCode();

        // Change snake direction
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { // Up
            snake.setDirection(Directions.UP);
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) { // Down
            snake.setDirection(Directions.DOWN);
        } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) { // Left
            snake.setDirection(Directions.LEFT);
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) { // Right
            snake.setDirection(Directions.RIGHT);
        }

        // Restart the game
        if ((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) && GamePanel.gameOver) {
            System.out.println("Restarting");
            game.restart();
        }

        // Pause the game
        if ((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_P) && !GamePanel.gameOver) {
            System.out.println(snake.isPaused());
            if (!snake.isPaused()) {
                snake.pause();
            } else {
                snake.unpause();
            }
        }

        // Mute or unmute the game
        if (key == KeyEvent.VK_M) {
            try {
                if (Game.musicPlayer.isPlaying()) {
                    Game.musicPlayer.pause();
                } else {
                    Game.musicPlayer.resume();
                }
                System.out.println("Game.musicPlayer.getLength() = " + Game.musicPlayer.getLength());
            } catch (IOException | JavaLayerException ex) {
                ex.printStackTrace();
            }
        }
    }
}
