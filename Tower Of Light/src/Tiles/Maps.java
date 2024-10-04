package Tiles;

import Main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static Tiles.Tiles.NUMBER_OF_TILES;
import static Tiles.Tiles.TILE_SIZE;
import static java.lang.System.exit;

public class Maps {
    GamePanel gamePanel;
    Tiles tile;
    public int currentMap = 1; //επιπεδο

    public int[][] mapTiles = new int[NUMBER_OF_TILES][NUMBER_OF_TILES]; //για την αποθήκευση των δεδομένων απο τα αρχεία txt με τους χάρτες
    public boolean[][] visitedTiles = new boolean[NUMBER_OF_TILES][NUMBER_OF_TILES]; //πινακας με τα πλακίδια τα οποία έχουμε επισκεφτεί

    public boolean[][] beaconTiles = new boolean[NUMBER_OF_TILES][NUMBER_OF_TILES]; //πλακίδια με beacons

    public Maps(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tiles(gamePanel);
        loadMap(currentMap);

        // αρχικοποίηση των visitedTiles και beaconTiles
        for (int row = 0; row < NUMBER_OF_TILES; row++) {
            for (int col = 0; col < NUMBER_OF_TILES; col++) {
                visitedTiles[row][col] = false;
                beaconTiles[row][col] = false;
            }
        }
    }

    // μέθοδος για φόρτωση του χάρτη βάσει επιπέδου
    public void loadMap(int level) {
        try {
            String file = "/Tiles/MAP0" + level + ".txt";
            InputStream is = getClass().getResourceAsStream(file); //φόρτωση του αρχείου
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //διαβασμα του αρχείου

            int row = 0;

            while (row < NUMBER_OF_TILES) { // βρόγχος για το διάβασμα όλων των γραμμων
                String line = br.readLine(); //διαβασμα κάθε γραμμής τους αρχείου
                String[] numbers = line.trim().split("\\s+"); //αποθηκευση των γραμμών χωρίς τα κενά στον πίνακα numbers

                for (int column = 0; column < NUMBER_OF_TILES; column++) {
                    int num = Integer.parseInt(numbers[column]);
                    mapTiles[row][column] = num;
                }
                row++;
            }
            br.close(); //κλείσιμο αρχείου
        } catch (Exception e) {
            System.err.println("Error in map file loading!!!");
            exit(0);
        }
    }

    public void draw(Graphics2D g2){
        int playerX = gamePanel.playerControl.getPlayerX();
        int playerY = gamePanel.playerControl.getPlayerY();
        int visibilityRadius = gamePanel.playerControl.VISIBILITY_RADIUS;

        for (int row = 0; row < NUMBER_OF_TILES; row++) {
            for (int column = 0; column < NUMBER_OF_TILES; column++) {
                int distanceX = Math.abs(column - playerX);
                int distanceY = Math.abs(row - playerY);

                if (distanceX <= visibilityRadius && distanceY <= visibilityRadius) {
                    visitedTiles[row][column] = true; //αλλαγή κατάσταση πλακίδιου
                    //ελεγχος αν εχει beacon
                    if (beaconTiles[row][column]) {
                        tile.tileCreationWithBeacon(mapTiles[row][column], column * TILE_SIZE, row * TILE_SIZE, g2); // σχεδίαση με beacon
                    } else {
                        tile.tileCreation(mapTiles[row][column], column * TILE_SIZE, row * TILE_SIZE, g2); // Κανονική σχεδίαση
                    }
                } else if (visitedTiles[row][column]) {
                    // σχεδίαση πλακιδίων που έχει ήδη επισκεφθεί ο παίχτης
                    if (beaconTiles[row][column]) {
                        tile.tileCreationWithBeacon(mapTiles[row][column], column * TILE_SIZE, row * TILE_SIZE, g2); // σχεδίαση με beacon
                    } else {
                        tile.tileCreation(mapTiles[row][column], column * TILE_SIZE, row * TILE_SIZE, g2); // κανονική σχεδίαση
                    }
                    g2.setColor(new Color(0, 0, 0, 150));
                    g2.fillRect(column * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                } else {
                    // σχεδιάση περιοχών που δεν έχει γίνει επίσκεψη ακόμα
                    g2.setColor(Color.BLACK);
                    g2.fillRect(column * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    public boolean canMove(int x, int y){//μέθοδος για τον έλεγχο εαν ενα πλακίδιο είναι κατειλημμένο
        return mapTiles[y][x] != 2;
    }
}
