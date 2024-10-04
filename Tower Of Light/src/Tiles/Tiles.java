package Tiles;

import Main.GamePanel;

import java.awt.*;

import static java.lang.System.exit;

public class Tiles {
    public static final int TILE_SIZE = 16;
    public static final int NUMBER_OF_TILES = 52;

    final Color TILE_BORDER_COLOR = Color.BLACK; //το χρώμα του περιγράμματος όλων των πλακιδίων
    final  Color GRASS_COLOR = new Color(80,80,10);; //το χρώμα των πλακιδίων του πατώματος
    final  Color WALL_COLOR = new Color(0,0,0);; //το χρώμα των πλακιδίων των τοίχων
    final  Color ENTRANCE_COLOR = new Color(120,120,220);; //το χρώμα του πλακιδίου κάτω αριστερά
    final  Color EXIT_COLOR = new Color(220,220,220);; //το χρώμα του πλακιδίου πάνω δεξιά

    GamePanel gamePanel;

    public Tiles(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void tileCreation(int i, int x, int y, Graphics2D g2) {

        Color[] tileColors = {ENTRANCE_COLOR, EXIT_COLOR, WALL_COLOR, GRASS_COLOR}; // πίνακας που αποθηκεύει τα χρώματα για κάθε τύπο πλακιδίου

        try {
            g2.setColor(TILE_BORDER_COLOR);
            g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
            g2.setColor(tileColors[i]);
            g2.fillRect(x, y, TILE_SIZE - 1, TILE_SIZE - 1);
        } catch (IndexOutOfBoundsException e) {// Διαχείριση της εξαίρεσης
            System.err.println("Error in map file!!!");
            exit(0);
        }
    }

    //μεθοδος για τη σχεδιαση πλακιδίου με beacon
    public void tileCreationWithBeacon(int i, int x, int y, Graphics2D g2) {
        tileCreation(i, x, y, g2); // Σχεδίαση του πλακιδίου κανονικά
        g2.setColor(new Color(255, 215, 0, 128));
        g2.fillRect(x, y, TILE_SIZE - 1, TILE_SIZE - 1);
    }
}



