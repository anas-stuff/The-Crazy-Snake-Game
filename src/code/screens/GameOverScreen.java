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
        g.drawString(str,
                (WIDTH - g.getFontMetrics().stringWidth(str)) / 2,
                HEIGHT / 3);
        g.setFont(new Font(g.getFont().getFamily(), Font.BOLD, 35));
        g.drawString(str = "Score: " + score,
                (game.getWidth() - g.getFontMetrics().stringWidth(str)) / 2,
                game.getHeight() / 2 - 50);
        g.drawString(str = "High Score: " + highScore,
                (game.getWidth() - g.getFontMetrics().stringWidth(str)) / 2,
                game.getHeight() / 2);
        g.drawString(str = "Level: " + level,
                (game.getWidth() - g.getFontMetrics().stringWidth(str)) / 2,
                game.getHeight() / 2 + 50);
        g.drawString("Lives: " + lives,
                (game.getWidth() - g.getFontMetrics().stringWidth(str)) / 2,
                game.getHeight() / 2 + 100);
        g.drawString(str = "Press Space or Enter to Play Again",
                (game.getWidth() - g.getFontMetrics().stringWidth(str)) / 2,
                game.getHeight() / 2 + 150);
        if (newHighScore) {
            g.drawString(str = "New High Score!",
                    (game.getWidth() - g.getFontMetrics().stringWidth(str)) / 2,
                    game.getHeight() / 2 + 200);
        }
    }
}
