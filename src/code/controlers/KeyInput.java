package code.controlers;

import code.GamePanel;
import code.enums.Directions;
import code.gameComponents.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    // Variables
    private Snake snake;

    // Constructor
    public KeyInput(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { // Up
            snake.setDirection(Directions.UP);
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) { // Down
            snake.setDirection(Directions.DOWN);
        } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) { // Left
            snake.setDirection(Directions.LEFT);
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) { // Right
            snake.setDirection(Directions.RIGHT);
        }
    }
}
