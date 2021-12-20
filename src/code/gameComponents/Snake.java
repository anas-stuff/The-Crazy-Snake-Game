package code.gameComponents;

import code.Game;
import code.GamePanel;
import code.enums.Directions;
import code.media.SFX;

import java.awt.*;

public class Snake {
    private int length; // length of the snake or number of segments in the snake
    private int[] x; // x-coordinates of snake
    private int[] y; // y is the column
    private int score, highScore; // number of apples eaten
    private Directions direction; // Direction of the snake
    private Color headColor; // Color of the head
    private Color bodyColor; // Color of the body
    private boolean paused; // if the game is paused
    private boolean soundPlayed; // if the sound has been played
    public Snake(Color headColor, Color bodyColor) {
        x = new int[GamePanel.GAME_UNITS];
        y = new int[GamePanel.GAME_UNITS];
        reset();
        // Initialize the head color
        this.headColor = headColor;
        // Initialize the body color
        this.bodyColor = bodyColor;
    }

    // The move method.
    public void move() {
        // Check if the game is paused
        if (!paused) {
            // Move all the segments of the snake one step forward
            for (int i = length; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }

            // Move the head of the snake based on the direction
            switch (direction) {
                case UP -> y[0] -= GamePanel.UNIT_SIZE;
                case DOWN -> y[0] += GamePanel.UNIT_SIZE;
                case LEFT -> x[0] -= GamePanel.UNIT_SIZE;
                case RIGHT -> x[0] += GamePanel.UNIT_SIZE;
            }
        }
    }

    // The pause method.
    public void pause() {
        // Pause the snake
        paused = true;
    }

    // The unpause method.
    public void unpause() {
        // Unpause the snake
        paused = false;
    }

    // Checks if the snake has collided with itself or corners
    public void checkCollision(boolean andCorners) {
        // Check if the snake has collided with itself
        for (int i = length; i > 0; --i) {
            if (x[0] == x[i] && y[0] == y[i]) { // Head collided with body
                GamePanel.gameOver = true;
                break;
            }
        }
        // Check if the snake has collided with the corners
        if (andCorners) {
            if (x[0] < 0 || x[0] > GamePanel.WIDTH) { // Head collided with left corner or right corner
                GamePanel.gameOver = true;
            } else if (y[0] < 0 || y[0] > GamePanel.HEIGHT) { // Head collided with top corner or bottom corner
                GamePanel.gameOver = true;
            }
            if (GamePanel.gameOver && !soundPlayed) {
                // Play collision sound
                Game.sfxPlayer.play(SFX.SnakeCollided);
                // Set the sound played to true
                soundPlayed = true;
            }
        }
    }

    // The rest method.
    public void reset() {
        // Reset the length of the snake to 6
        length = 6;
        // Reset the apple eaten to 0
        score = 0;
        // Reset the high score to 0 temporarily
        highScore = 0;
        // Reset the direction to right
        direction = Directions.RIGHT;
        // Initialize the snake to be paused
        paused = false;
        // Initialize the sound played to false
        soundPlayed = false;

        // Initialize the x-coordinates and y-coordinates of the snake
        for (int i = 1; i < length; i++) {
            x[i] = -i * GamePanel.UNIT_SIZE;
            y[i] = -i * GamePanel.UNIT_SIZE;
        }
        // Initialize the head of the snake
        x[0] = 0;
        y[0] = 0;
    }

    // The getters and setters
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        // If the key is pressed, the snake will move in the direction of the key
        if (isPaused()) {
            unpause();
        }
        // Check if the direction is not opposite to the current direction
        if (!this.direction.getOpposite().equals(direction.name())) {
            this.direction = direction;
        }
    }

    public void addUnit() {
        length++;
        score++;
    }

    public Color getHeadColor() {
        return headColor;
    }

    public void setHeadColor(Color headColor) {
        this.headColor = headColor;
    }

    public Color getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getHighScore() {
        return highScore;
    }
}
