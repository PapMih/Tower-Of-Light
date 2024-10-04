package Characters;

import Items.Item;
import Items.UsableItems;
import Items.Weapons;
import Main.GameHandler;
import Main.GamePanel;
import Tiles.Maps;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static Characters.Player.*;
import static Characters.Player.getManaPointsInt;
import static Items.Equippable.getDamage;
import static Items.ItemDesign.*;
import static Tiles.Tiles.NUMBER_OF_TILES;
import static Tiles.Tiles.TILE_SIZE;
import static java.lang.System.exit;

public class PlayerControl extends Characters {

    public final int VISIBILITY_RADIUS = 6 ;

    static GamePanel gamePanel;
    GameHandler gameHandler;
    Maps map;
    static EnemiesDesign enemies;


    final Color PLAYER_COLOR = new Color(100, 30, 80); // το χρώμα του πλακιδίου του παίκτη
    final Color PLAYER_BORDER_COLOR = Color.BLACK;

    public PlayerControl(GamePanel gamePanel, GameHandler gameHandler, Maps map, EnemiesDesign enemies) {
        this.gamePanel = gamePanel;
        this.gameHandler = gameHandler;
        this.map = map;
        this.enemies = enemies;
        this.setInitialValues();
    }

    public void setInitialValues() {
        x = 1 * TILE_SIZE;
        y = (NUMBER_OF_TILES - 2) * TILE_SIZE;
    }

    public void movePlayer(int dx, int dy) {
        int newX = x + dx * TILE_SIZE;
        int newY = y + dy * TILE_SIZE;

        // ελέγχουμε αν οι νέες συντεταγμένες επιτρέπουν κίνηση
        if (map.canMove(newX / TILE_SIZE, newY / TILE_SIZE)) {
            x = newX;
            y = newY;

            int playerTileX = x / TILE_SIZE;
            int playerTileY = y / TILE_SIZE;
            map.visitedTiles[playerTileY][playerTileX] = true; //αλλαγή κατάστασης πλακιδίου

            // έλεγχος για το αν εχουν ολοκληρωθεί 3 beacons και ο παίχτης είναι στο πάνω δεξιά πλακίδιο
            if (playerTileX == NUMBER_OF_TILES - 2 && playerTileY == 1) {
                if (Player.beaconTiles.size() >= 3) {
                    proceedToNextLevel();
                } else {
                    gamePanel.logMessage("You need to activate 3 beacons to exit the level.");
                }

                //enemies.enemiesMovement(newX, newY);
                itemsInTileCheck(x, y);
                gamePanel.repaint();
            }
        }
    }

    public void movePlayerLeft() {
        movePlayer(-1, 0); // Κίνηση προς τα αριστερά
    }

    public void movePlayerRight() {
        movePlayer(1, 0); // Κίνηση προς τα δεξιά
    }

    public void movePlayerUp() {
        movePlayer(0, -1); // Κίνηση προς τα πάνω
    }

    public void movePlayerDown() {
        movePlayer(0, 1); // Κίνηση προς τα κάτω
    }

    //επιστρέφει την χ συντεταγμένη του παίχτη
    public int getPlayerX(){
        return (x / TILE_SIZE);
    }

    //επιστρέφει την y συντεταγμένη του παίχτη
    public int getPlayerY(){
        return (y / TILE_SIZE);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(PLAYER_BORDER_COLOR);
        g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        g2.setColor(PLAYER_COLOR);
        g2.fillRect(x, y, TILE_SIZE - 1, TILE_SIZE - 1);
    }

    public void playerWeaponAttack(int playerX, int playerY, EnemiesDesign enemiesDesign) {
        Map<String, Integer> hpEnemies = new HashMap<>();

        for (String key : EnemiesDesign.enemiesMap.keySet()) {
            int enemyX = Integer.parseInt(key.split(",")[0]) * TILE_SIZE;
            int enemyY = Integer.parseInt(key.split(",")[1]) * TILE_SIZE;
            int distanceX = enemyX - playerX * TILE_SIZE;
            int distanceY = enemyY - playerY * TILE_SIZE;

            if (Math.abs(distanceX) <= TILE_SIZE && Math.abs(distanceY) <= TILE_SIZE ) { // αν ο εχθρός είναι σε διπλανό πλακίδιο από τον παίκτη
                int[] enemyDamage = new int[3]; // πίνακας με το damage ανά είδος επίθεσης
                int finalDamage = 0; // το τελικό ποσό ζημιάς

                // λήψη damage από το main hand όπλο
                int[] mainHandDamage = getDamage(gamePanel.player.getMainHandName());

                // αρχικοποίηση του offHandDamage
                int[] offHandDamage = new int[3];
                offHandDamage[0] = 0;
                offHandDamage[1] = 0;
                offHandDamage[2] = 0;

                // έλεγχος αν υπάρχει όπλο στο off hand
                if (gamePanel.player.offHand != null) {
                    // Λήψη damage από το off hand όπλο
                    offHandDamage = getDamage(gamePanel.player.offHand);
                }

                // συνολικό damage από τα δύο όπλα
                int[] totalWeaponDamage = new int[3];
                for (int i = 0; i < 3; i++) {
                    totalWeaponDamage[i] = mainHandDamage[i] + offHandDamage[i];
                }

                int[] enemyDefense = EnemiesDesign.enemiesMap.get(key).res;

                // υπολογισμός τελικού damage μετά την αφαίρεση της άμυνας του εχθρού
                for(int i = 0; i <= 2; i++){
                    enemyDamage[i] = totalWeaponDamage[i] - enemyDefense[i];
                    if(enemyDamage[i] < 0){ // Σε περίπτωση που η ζημιά είναι αρνητική, την κάνουμε μηδέν
                        enemyDamage[i] = 0;
                    }
                    finalDamage += enemyDamage[i];
                }
                hpEnemies.put(key, finalDamage); // προσθήκη συντεταγμένων και της ζημιάς στον χάρτη
                gamePanel.logMessage("You attacked an " + EnemiesDesign.enemiesMap.get(key).name + ". Amount of damage: " + finalDamage );
            }
        }
        enemiesDesign.hitPointReduce(hpEnemies); // κλήση της μεθόδου που μειώνει το hp των εχθρών
    }

    public static void playerCollectItem(int type, int playerX, int playerY) {
        for (String key : itemsMap.keySet()) {
            ArrayList<Item> items = itemsMap.get(key);
            int itemX = Integer.parseInt(key.split(",")[0]);
            int itemY = Integer.parseInt(key.split(",")[1]);
            if (playerX == itemX && playerY == itemY) {
                boolean itemCollected = false;
                //type 1 ειναι για main hand
                if (type == 1) {
                    //ελεγχος όλων των αντικειμένων στη λίστα του πλακιδίου, επιλογη γίνεται του πρώτου στην λίστα που είναι κατάλληλο για main hand
                    Iterator<Item> iterator = items.iterator();
                    while (((Iterator<?>) iterator).hasNext()) {
                        Item item = iterator.next();
                        if (item instanceof Weapons) {
                            if (item == Player.mainHand) {
                                break; //εάν έχει ήδη το όπλο αυτό παραλείπεται η διαδικασία
                            }
                            Weapons temp = Player.mainHand;
                            getItemInMainHand((Weapons) item);
                            iterator.remove(); // Αφαιρούμε το αντικείμενο από τη λίστα
                            gamePanel.logMessage("You collected " + ((Weapons) item).getEquippableName() + ". Description: " + ((Weapons) item).getEquippableDescription() + ". Slot: Main hand.");
                            if (temp != null) {
                                items.add(temp); // προσθέτω το παλιό αντικείμενο πίσω
                            }
                            itemCollected = true;
                            enemies.enemiesMovement(playerX, playerY); //όταν επιλέγω αντικείμενο οι εχθροί κινούνται
                            gamePanel.repaint();
                            break;
                        }
                    }
                }
                if (type == 2) {
                    //ελεγχος όλων των αντικειμένων στη λίστα του πλακιδίου, επιλογή γίνεται του πρώτου στην λίστα που είναι κατάλληλο για off hand
                    Iterator<Item> iterator = items.iterator();
                    while (iterator.hasNext()) {
                        Item item = iterator.next();
                        if (item instanceof Weapons) {
                            if (item == offHand) {
                                break; //εάν έχει ήδη το όπλο αυτό παραλείπεται η διαδικασία
                            }
                            Weapons temp = Player.offHand;
                            getItemInOffHand((Weapons) item);
                            iterator.remove(); // αφαιρώ το αντικείμενο από τη λίστα
                            gamePanel.logMessage("You collected " + ((Weapons) item).getEquippableName() + ". Description: " + ((Weapons) item).getEquippableDescription() + ". Slot: Off hand.");
                            if (temp != null) {
                                items.add(temp); // προσθέτω το παλιό αντικείμενο πίσω
                            }
                            itemCollected = true;
                            enemies.enemiesMovement(playerX, playerY); //όταν επιλέγω αντικείμενο οι εχθροί κινούνται
                            gamePanel.repaint();
                            break;
                        }
                    }
                }
                if (type == 3) {
                    //ελεγχος όλων των αντικειμένων στη λίστα του πλακιδίου, επιλογή γίνεται του πρώτου στην λίστα που είναι κατάλληλο για trinket
                    Iterator<Item> iterator = items.iterator();
                    while (iterator.hasNext()) {
                        Item item = iterator.next();
                        if (item instanceof UsableItems) {
                            UsableItems temp = Player.trinket;
                            getItemInTrinket((UsableItems) item);
                            iterator.remove(); // αφαιρώ το αντικείμενο από τη λίστα
                            gamePanel.logMessage("You collected " + ((UsableItems) item).getUsableName() + ". Description: " + ((UsableItems) item).getUsableDescription() + ". Slot: trinket.");
                            if (temp != null) {
                                items.add(temp); // προσθετω το παλιό αντικείμενο πίσω
                            }
                            itemCollected = true;
                            enemies.enemiesMovement(playerX, playerY); //όταν επιλέγω αντικείμενο οι εχθροί κινούνται
                            gamePanel.repaint();
                            break;
                        }
                    }
                }
                // μετά από κάθε συλλογή αντικειμένου, ελέγχουμε αν η λίστα είναι άδεια
                if (itemCollected) {
                    if (items.isEmpty()) {
                        itemsMap.remove(key); // αφαιρώ το κλειδί από το itemsMap αν η λίστα είναι κενή
                    }
                }
                break; // εχουμε βρει το πλακίδιο, δεν χρειάζεται να συνεχίσουμε
            }
        }
    }

    //μεθοδος που αυξάνει 5% τα hit points
    public void restoration() {
        int amount = (int) Math.ceil(getManaPointsInt() * 0.05); //υπολογισμός του ποσού της αύξησης των Mana Points
        manaPointIncreaseByRestoration(amount); //καλώ τη μέθοδο που αυξάνει τα Mana Points
        amount = (int) Math.ceil(getHitPointsInt() * 0.05); //υπολογισμός του ποσού της αύξησης των Hit Points
        hitPointIncreaseByRestoration(amount); //καλώ τη μέθοδο που αυξάνει τα Hit Points
    }

    //μεθοδος για επίθεση με ξορκι
    public void playerSpellAttack(int playerX, int playerY, EnemiesDesign enemiesDesign) {
        Map<String, Integer> hpEnemies = new HashMap<>();
        int amount;
        for (String key : EnemiesDesign.enemiesMap.keySet()) {
            int enemyX = Integer.parseInt(key.split(",")[0]) * TILE_SIZE;
            int enemyY = Integer.parseInt(key.split(",")[1]) * TILE_SIZE;
            int distanceX = enemyX - playerX * TILE_SIZE;
            int distanceY = enemyY - playerY * TILE_SIZE;

            if (Math.abs(distanceX) <= (VISIBILITY_RADIUS * TILE_SIZE) && Math.abs(distanceY) <= (VISIBILITY_RADIUS * TILE_SIZE) && (getManaPointsInt() > 0) ) { // εάν ο εχθρός είναι εντος ορατοτ
                int finalDamage = getIntellectInt(); //το τελικο damage ισούται με το intellect αφου δεν εχω οπλα και αντικειμενα που να δίνουν μπονους
                hpEnemies.put(key,finalDamage);//προσθηκη συντενταγμένων και της ζημιάς στον χάρτη
                gamePanel.logMessage("You attacked an " +  EnemiesDesign.enemiesMap.get(key).name + ". Amount of damage: " + finalDamage );
                amount = (int) Math.ceil(getManaPointsInt() * 0.05); //υπολογισμός του ποσού της μείωσης των Mana Points, στρογγυλοποιηση προς τα πανω για να μη βγει ποτε 0
                manaPointDecreaseBySpellAttack(amount); //καλώ τη μέθοδο που μειώνει τα Mana Points
               // System.out.println(amount);
            }
        }
        enemiesDesign.hitPointReduce(hpEnemies);//κλήση της μεθόδου που μειώνει το hp των εχθρών
    }

    //μεθοδος για την εκτελεση beacons
    public void castBeaconSpell() {
        //ελεγχος αν εχει γινει cooldown
        if (Player.beaconCooldown > 0) {
            gamePanel.logMessage("The spell is on cooldown for " + Player.beaconCooldown + " turns.");
            return;
        }

        int playerTileX = x / TILE_SIZE;
        int playerTileY = y / TILE_SIZE;

        //εεγχος απόστασης από τα υπάρχοντα beacons
        for (Point beacon : Player.beaconTiles) {
            int distance = Math.abs(beacon.x - playerTileX) + Math.abs(beacon.y - playerTileY);
            if (distance < 10) {
                gamePanel.logMessage("You must be at least 10 tiles away from other beacons.");
                return;
            }
        }

        //εκτέλεση του ξορκιού
        Player.beaconCooldown = 15; // θετουμε το cooldown
        Player.beaconTiles.add(new Point(playerTileX, playerTileY)); // τοπθέτηση του beacon στον χάρτη
        enemies.updateEnemiesOnBeaconChange(); //αλλαγή του αριθμόυ των εχθρών

        // θέλω τα πλακίδια με beacon να είναι πάντα ορατά
        map.visitedTiles[playerTileY][playerTileX] = true;
        map.beaconTiles[playerTileY][playerTileX] = true;

        gamePanel.logMessage("You have cast the Beacon Spell!");

        //ελεγχος αν έχουν τοποθετηθεί 3 beacons
        if (Player.beaconTiles.size() >= 3) {
            //αλλαγή επιπέδου
            changeLevel();
        }
    }

    // μέθοδος για τη μείωση του cooldown σε κάθε γύρο
    public void reduceBeaconCooldown() {
        if (Player.beaconCooldown > 0) {
            Player.beaconCooldown--;
        }
    }

    // μέθοδος για την αλλαγή του επιπέδου
    public void changeLevel() {
        gamePanel.logMessage("You can change level");
    }

    //μεθοδος για την εισαγωγή επόμενης πίστας
    public void proceedToNextLevel() {
        //επαναφορα των beacons
        Player.beaconTiles.clear();
        Player.beaconCooldown = 0;

        map.currentMap++;
        if (map.currentMap > 6) {
            // το παιχνίδι τελείωσε
            gamePanel.logMessage("Congratulations! You have completed the game.");
            exit(0);
        } else {
            // φόρτωση του επόμενου επιπέδου
            map.loadMap(map.currentMap);
            // επαναφορά των visitedTiles και beaconTiles
            for (int row = 0; row < NUMBER_OF_TILES; row++) {
                for (int col = 0; col < NUMBER_OF_TILES; col++) {
                    map.visitedTiles[row][col] = false;
                    map.beaconTiles[row][col] = false;
                }
            }
            // επαναφορά των beacons
            Player.beaconTiles.clear();
            Player.beaconCooldown = 0;

            // επαναφορά θέσης παίκτη
            setInitialValues();

            // επαναφορά εχθρών
            gamePanel.enemies = new EnemiesDesign(gamePanel, map);

            gamePanel.logMessage("Level " + map.currentMap + " loaded.");
            gamePanel.repaint();
        }
    }
}
