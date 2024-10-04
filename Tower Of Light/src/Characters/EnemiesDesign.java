package Characters;

import Main.GamePanel;
import Tiles.Maps;

import java.awt.*;
import java.util.*;
import java.util.List;

import static Characters.Player.beacons;
import static Characters.Player.getLevel;
import static Items.ItemDesign.itemPlacement;
import static Tiles.Tiles.NUMBER_OF_TILES;
import static Tiles.Tiles.TILE_SIZE;

public class EnemiesDesign {

    final Color ENEMIES_COLOR = new Color(200, 200, 180); // το χρώμα του πλακιδίου του παίκτη
    final Color ENEMIES_BORDER_COLOR = Color.BLACK;

    GamePanel gamePanel;
    Maps map;

    static Set<String> coordinates = new HashSet<>(); //χαρτης με μοναδικές τιμές τις συντεταγμένες των εχθρών
    static HashMap<String, Enemies> enemiesMap = new HashMap<>(); //χάρτης με κλειδί τις συντεταγμένες και το είδος του εχθρού σε κάθε πλακίδιο

    public EnemiesDesign(GamePanel gamePanel, Maps map) {
        this.gamePanel = gamePanel;
        this.map = map;

        coordinates.clear(); // μηδενισμός τις συντεταγμένες των εχθρών
        enemiesMap.clear(); // μηδενισμός του χάρτη των εχθρών

        int numberOfBeacons = Player.beaconTiles.size(); // λήψη του αριθμού των beacons
        int totalEnemies = 3 + 2 * numberOfBeacons; // υπολογισμός του συνολικού αριθμού εχθρών

        for (int i = 1; i <= totalEnemies; i++) {
            enemyPlacement(EnemiesLevel.randomSelection("LEVEL" + getLevel()));
        }
    }

    //μέθοδος η οποία δεχεται τύπο εχθρού και παράγει τυχαίες συντεταγμένες
    public void enemyPlacement(Enemies item) {
        Random rand = new Random();
        int x, y;

        do {
            x = rand.nextInt(52);
            y = rand.nextInt(52);
        } while (!isValidEnemyPosition(x, y)); // Χρήση της νέας μεθόδου

        // Προσθέτουμε τον εχθρό στον χάρτη
        addEnemyInMap(x, y, item);
        coordinates.add(x + "," + y);
    }

    public boolean isValidEnemyPosition(int x, int y) {
        // ελεγχος αν το πλακίδιο είναι τοίχος
        if (!map.canMove(x, y)) {
            return false;
        }

        // ελεγχος αν υπάρχει ήδη εχθρός στις συντεταγμένες
        if (tileEnemyCheck(x, y)) {
            return false;
        }

         //αν το playerControl δεν είναι αρχικοποιημένο δεν γινεται ελεγχος γιατι προκύπτει σφάλμα
        if (gamePanel.playerControl == null) {
            return true;
        }

        // θέση παίκτη και ακτίνα ορατότητας
        int playerX = gamePanel.playerControl.getPlayerX();
        int playerY = gamePanel.playerControl.getPlayerY();
        int visibilityRadius = gamePanel.playerControl.VISIBILITY_RADIUS;

        // υπολογισμός της απόστασης του παίκτη από τις συντεταγμένες
        int distanceX = Math.abs(x - playerX);
        int distanceY = Math.abs(y - playerY);

        // ελεγχος αν οι συντεταγμένες βρίσκονται εντός του οπτικού πεδίου του παίκτη
        if (distanceX <= visibilityRadius && distanceY <= visibilityRadius) {
            return false;
        }
        return true;
    }

    //μεθοδος η οποία καλειται από την enemyPlacement για την τοποθέτηση των εχθρών στους χάρτες
    void addEnemyInMap(int x, int y, Enemies enemy) {
        String key = x + "," + y;
        if (coordinates.add(key)) {
            enemiesMap.put(key, enemy); // προσθέτει στη λίστα τον συγκεκριμένο εχθρό
        }
    }

    public void draw(Graphics2D g2) {
        int playerX = gamePanel.playerControl.getPlayerX();
        int playerY = gamePanel.playerControl.getPlayerY();
        int visibilityRadius = gamePanel.playerControl.VISIBILITY_RADIUS;

        for (String key : enemiesMap.keySet()) {
            int enemyX = Integer.parseInt(key.split(",")[0]);
            int enemyY = Integer.parseInt(key.split(",")[1]);

            int distanceX = Math.abs(enemyX - playerX);
            int distanceY = Math.abs(enemyY - playerY);

            // οι εχθροι εκτός οπτικου πεδίου δεν σχεδιάζονται
            if (distanceX <= visibilityRadius && distanceY <= visibilityRadius) {
                int x = enemyX * TILE_SIZE;
                int y = enemyY * TILE_SIZE;
                g2.setColor(ENEMIES_BORDER_COLOR);
                g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                g2.setColor(ENEMIES_COLOR);
                g2.fillRect(x + 1, y + 1, TILE_SIZE - 2, TILE_SIZE - 2);
            }
        }
    }

    //έλεγχος αν υπάρχει ήδη εχθρός στο πλακίδιο
    static boolean tileEnemyCheck(int x, int y) {
        String key = x + "," + y;
        return enemiesMap.containsKey(key);
    }

    //επιστρέφει τον τύπο του εχθρού που ειναι στο συγκεκριμένο πλακίδιο
    Enemies getEnemyInTile(int x, int y) {
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        return enemiesMap.get(key);
    }

    //μεθοδος για την κίνηση των αντιπάλων
    public void enemiesMovement(int playerX, int playerY) {
        Map<String, Enemies> updatedEnemiesMap = new HashMap<>();

        //υπολογισμοί για όλους τους εχθρους
        for (String key : enemiesMap.keySet()) {
            int enemyX = Integer.parseInt(key.split(",")[0]);
            int enemyY = Integer.parseInt(key.split(",")[1]);

            // υπολογισμός διαφοράς από τον παίκτη
            int distanceX = enemyX - playerX;
            int distanceY = enemyY - playerY;

            //υπολογισμός νέας θέση
            int newEnemyX, newEnemyY;
            if (Math.abs(distanceX) < Math.abs(distanceY)) {
                newEnemyY = enemyY + (distanceY > 0 ? -1 : 1);
                newEnemyX = enemyX;
            } else {
                newEnemyX = enemyX + (distanceX > 0 ? -1 : 1);
                newEnemyY = enemyY;
            }

            String newKey = newEnemyX + "," + newEnemyY;

            // έλεγχος εγκυρότητας της νέας θέσης
            if (!tileCheck(newEnemyX, newEnemyY) && !(playerX == newEnemyX && playerY == newEnemyY) && coordinates.add(newKey)) {
                // ενημέρωση θέσης εχθρού
                updatedEnemiesMap.put(newKey, enemiesMap.get(key));
                coordinates.remove(key);
            } else {
                // αν η νέα θέση δεν είναι έγκυρη, παραμένει στην ίδια θέση
                updatedEnemiesMap.put(key, enemiesMap.get(key));
            }
        }
        // ενημέρωση του χάρτη με τις νέες θέσεις
        enemiesMap.clear();
        enemiesMap.putAll(updatedEnemiesMap);
        gamePanel.repaint();
    }

    //μεθοδος για τον έλεγχο του ότι δεν υπάρχει άλλος εχθρός στο πλακίδιο και ότι δεν είναι "τοιχος"
    boolean tileCheck(int x, int y){
        String key = x + "," + y;
        return ((tileEnemyCheck(x, y)) || (!map.canMove(x, y)) || ((x == 1) && (y == (NUMBER_OF_TILES - 2))) || ((x == (NUMBER_OF_TILES - 2)) && (y == 1)));
    }

    //μειωση hitpoints εχθρού
    public void hitPointReduce(Map<String, Integer> hpEnemies) {
        List<String> enemiesToRemove = new ArrayList<>(); // λίστα με εχθρούς που πρέπει να αφαιρεθούν

        for (String key : hpEnemies.keySet()) {
            enemiesMap.get(key).hp = enemiesMap.get(key).hp - hpEnemies.get(key); //μειώνω το hp των εχθρών
            if (enemiesMap.get(key).hp <= 0) {
                gamePanel.player.increaseXP(enemiesMap.get(key).getXpPoints());;
                enemiesToRemove.add(key); // προσθήκη του εχθρού στη λίστα για αφαίρεση
            }
        }
        // αφαίρεση των εχθρών
        for (String key : enemiesToRemove) {
            enemiesMap.remove(key); // αφαίρεση του εχθρού από τον χάρτη
            coordinates.remove(key); // αφαίρεση των συντεταγμένων του εχθρού
            Random random = new Random();
            int randomPlacement = random.nextInt(3);
            if (randomPlacement == 1 || randomPlacement == 2){ // αν ο τυχαιος αριθμός είναι 1 ή 2 προσθέτω αντικείμενο
                int x = Integer.parseInt(key.split(",")[0]) * TILE_SIZE;
                int y = Integer.parseInt(key.split(",")[1]) * TILE_SIZE;
                itemPlacement(x, y, randomPlacement);
            }
        }
        maintainEnemyCount(); // έλεγχος αριθμού εχθρών
        gamePanel.repaint(); //επανασχεδιασμός του panel
    }

    // μέθοδος για τον έλεγχο του αριθμού των εχθρών στον χάρτη
    public void maintainEnemyCount() {
        int requiredEnemies = 3 + 2 * Player.beaconTiles.size(); // υπολογισμός του αριθμού των εχθρών
        int currentEnemies = enemiesMap.size(); //πόσοι εχθροί είναι τώρα στον χάρτη

        while (currentEnemies < requiredEnemies) {
            enemyPlacement(EnemiesLevel.randomSelection("LEVEL" + getLevel())); // Προσθήκη νέου εχθρού
            currentEnemies++;
        }
    }

    // μεθοδος για τον σχεδιασμό νέων εχθρών όταν γίνεται ένα beacon
    public void updateEnemiesOnBeaconChange() {
        maintainEnemyCount();
        gamePanel.repaint();
    }
}


