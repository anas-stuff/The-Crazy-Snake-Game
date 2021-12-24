package com.anas.code.gameComponents;

import com.anas.code.GamePanel;

import java.awt.*;
import java.util.Random;

public class Apple {
    private Color color;
    private int x;
    private int y;
    private Random rand;
    public Apple(Color color) {
        // Initialize the random number generator
        rand = new Random();
        this.color = color;
        // Generate a random x and y position for the apple
        generateApple();
    }

    public void generateApple() {
        // Generate a random x and y position for the apple
        x = rand.nextInt((int) (GamePanel.WIDTH / GamePanel.UNIT_SIZE)) * GamePanel.UNIT_SIZE;
        y = rand.nextInt((int) (GamePanel.HEIGHT / GamePanel.UNIT_SIZE)) * GamePanel.UNIT_SIZE;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
