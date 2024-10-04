package Main;

import javax.swing.*;

public class Main {
    public static int gameState = 0; // η τιμή είναι 0 για το title panel και 1 για το game panel
    static JFrame window;
    static TitlePanel titlePanel;
    static GamePanel gamePanel;

    public static void main(String[] args) {
        window = new JFrame("Tower Of Light") { // χρήση της static μεταβλητής window
            {
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setResizable(false);
                setLocationRelativeTo(null);
            }
        };

        titlePanel = new TitlePanel(); // χρήση της static μεταβλητής titlePanel
        window.add(titlePanel); // προσθήκη του στο παράθυρο
        window.pack();
        window.setVisible(true);
        titlePanel.requestFocusInWindow();
    }

    // μέθοδος για την δημιουργία του Game
    public static void updateGameState() {
        if (gameState == 1) {
            window.remove(titlePanel); // χρήση της static μεταβλητής titlePanel
            gamePanel = new GamePanel(); // χρήση της static μεταβλητής gamePanel
            window.add(gamePanel); // προσθήκη του στο παράθυρο
            window.pack();
            gamePanel.requestFocusInWindow();
            gameState = 2; // ενημέρωση ότι το παιχνίδι τρέχει
        }
    }

    // μέθοδος για την εναλλαγή των δύο Panel
    public static void setGameState(int state) {
        gameState = state;
        updateGameState();
    }
}
