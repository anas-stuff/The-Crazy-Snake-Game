package code;

public class Window extends javax.swing.JFrame {
    // The constructor
    public Window() {
        // Set the title
        setTitle("Snake");
        // Disable the resize option
        setResizable(false);
        // Close the window when the user clicks the close button
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Add the game panel to the window
        add(new GamePanel());
        // pack the window
        pack();
        // Set the location of the window in the center of the screen
        setLocationRelativeTo(null);
        // Show the window
        setVisible(true);
    }
}
