package code.screens;

import code.GamePanel;
import code.gameComponents.Snake;

import java.awt.*;

import static code.GamePanel.HEIGHT;
import static code.GamePanel.WIDTH;

public class GameOverScreen {
    private int score; // The player's score
    private int highScore; // The high score
    private int level; // The level the player was on
    private int lives; // The number of lives the player has left
    private boolean newHighScore; // Whether the player has a new high score
    private GamePanel game; // The game panel

    // Constructor
    public GameOverScreen(GamePanel game, Snake snake, Graphics g) {
        this.game = game;
        this.score = snake.getScore();
        this.highScore = snake.getHighScore();
        this.level = 0; // TODO: Implement level
        this.lives = 0; // TODO: Implement lives
        this.newHighScore = (score > highScore);
        // Draw the game over screen
        draw(g);
    }

    // Draw the game over screen method
    public void draw(Graphics g) {
        String str = "Game Over";
        // Draw the game over text
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        // Draw the game over screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        // Change the color of the text to red
        g.setColor(Color.RED);
        // Draw the game over text
        int strWidth = g.getFontMetrics().stringWidth(str);
        g.drawString(str, (WIDTH - strWidth) / 2, HEIGHT / 3);
        g.setFont(new Font(g.getFont().getFamily(), Font.BOLD, 35));
        g.drawString("Score: " + score, game.getWidth() / 2 - 50, game.getHeight() / 2 - 50);
        g.drawString("High Score: " + highScore, game.getWidth() / 2 - 100, game.getHeight() / 2);
        g.drawString("Level: " + level, game.getWidth() / 2 - 50, game.getHeight() / 2 + 50);
        g.drawString("Lives: " + lives, game.getWidth() / 2 - 50, game.getHeight() / 2 + 100);
        // Change str
        str = "Press Space or Enter to Play Again";
        strWidth = g.getFontMetrics().stringWidth(str);
        g.drawString(str, (game.getWidth() - strWidth) / 2, game.getHeight() / 2 + 150);
        if (newHighScore) {
            g.drawString("New High Score!", game.getWidth() / 2 - 100, game.getHeight() / 2 + 200);
        }
    }
}
