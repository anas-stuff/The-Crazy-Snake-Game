package com.anas.code;

import com.anas.code.controllers.KeyInput;
import com.anas.code.gameComponents.Apple;
import com.anas.code.gameComponents.Snake;
import com.anas.code.media.SFX;
import com.anas.code.screens.GameOverScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    // The screen width and height constants
    public static final int WIDTH = 600, HEIGHT = 600;
    // The unit size of the game
    public static final int UNIT_SIZE = 25;
    // The game units number
    public static final int GAME_UNITS = (WIDTH * HEIGHT) / UNIT_SIZE;
    // The Timer delay or game speed
    public static final int DELAY = 75;
    // Game start flag
    public static boolean gameStart = false;
    // Game over flag
    public static boolean gameOver = false;
    // Magick color for snick flag and apple
    public static boolean magickColorForSnick = true, magickColorForApple = true;
    // The snake
    private Snake snake;
    // The apple
    private Apple apple;
    // The game timer
    private Timer timer;

    // The constructor for the GamePanel class
    public GamePanel() {
        // Initialize the GamePanel
        init();
        // Set preferred size
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // Set background color
        setBackground(Color.BLACK);
        // Set focusable
        setFocusable(true);
        // Add key listener
        addKeyListener(new KeyInput(snake, this));
        // Start the game
        startGame();
    }

    // Initialize method
    private void init() {

        // Initialize the snake (player)
        snake = new Snake(Color.green, new Color(45, 180, 0));
        // Initialize the apple
        apple = new Apple(Color.RED);
        // Initialize the timer
        timer = new Timer(DELAY, this);
    }

    // Start game method
    public void startGame() {
        gameStart = true; // Set game start flag
        // Start the timer
        timer.start();

    }

    // Restart game method
    public void restart() {
        // Set game start flag
        gameStart = true;
        // Set game over flag
        gameOver = false;
        // Reset the snake
        snake.reset();
    }

    // Pint component method
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the super class paint component method
        draw(g); // Draw the game
    }

    // Draw method
    public void draw(Graphics g) {
        // If the game is started draw the game
        if (!gameStart && !gameOver) { // Draw the game start screen

        } else if (!gameOver) { // Draw the game
            // Draw the game matrix (optional)
            drawGameMatrix(g);
            // Draw the apple
            if (magickColorForApple) {
                g.setColor(new Color((int) (Math.random() * 255),
                        (int) (Math.random() * 255),
                        (int) (Math.random() * 255)));
            } else {
                g.setColor(apple.getColor());
            }
            g.fillOval(apple.getX(), apple.getY(), UNIT_SIZE, UNIT_SIZE);
            // Draw the snake
            for (int i = 0; i < snake.getLength(); i++) {
                if (i == 0) { // Draw the head
                    g.setColor(snake.getHeadColor());
                } else { // Draw the body
                    if (magickColorForSnick) {
                        g.setColor(new Color((int) (Math.random() * 255),
                                (int) (Math.random() * 255),
                                (int) (Math.random() * 255)));
                    } else {
                        g.setColor(snake.getBodyColor());
                    }
                }
                // fill the rectangle
                g.fillRect(snake.getX()[i], snake.getY()[i], UNIT_SIZE, UNIT_SIZE);
            }
            // Draw the score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Ink Free", Font.BOLD, 19));
            FontMetrics fm = g.getFontMetrics();
            g.drawString("Score: " + snake.getScore(),
                    (WIDTH - fm.stringWidth("Score: " + snake.getLength())) / 2,
                    g.getFont().getSize());
        } else { // Draw the game over screen
            new GameOverScreen(this, snake, g);
//            gameOver(g);
        }
    }

    // Draw the game matrix
    public void drawGameMatrix(Graphics g) {
        for (int i = 0; i < HEIGHT / UNIT_SIZE; i++) {
            // drawing x-axis lines
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, HEIGHT);
            // drawing y-axis lines
            g.drawLine(0, i * UNIT_SIZE, WIDTH, i * UNIT_SIZE);
        }
    }

    // Check apple method
    public void checkApple() {
        // Check if the snake is eating the apple
        if (snake.getX()[0] == apple.getX() && snake.getY()[0] == apple.getY()) {
            // Add one unit to the snake
            snake.addUnit();
            // Generate new apple
            apple.generateApple();
            // Play eat apple sound
            Game.sfxPlayer.play(SFX.SnakeEat);
        }
    }

    // Draw game over method
    public void gameOver(Graphics g) {

        FontMetrics fm = getFontMetrics(g.getFont());
        g.drawString("Game Over", (WIDTH - fm.stringWidth("Game Over")) / 2, HEIGHT / 2);
        // Draw the score
        g.setFont(new Font("Ink Free", Font.BOLD, 25));
        fm = getFontMetrics(g.getFont());
        g.drawString("Score: " + snake.getScore(),
                (WIDTH - fm.stringWidth("Score: " + snake.getScore())) / 2,
                HEIGHT / 2 + fm.getHeight());
    }

    // Action performed method
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the game is started
        if (gameStart) {
            // Start snake movement
            snake.move();
            // Check if the snake is eating the apple
            checkApple();
            // Check if the snake is colliding with itself
            snake.checkCollision(true);
        }
        // Repaint the game
        repaint();
    }
}
