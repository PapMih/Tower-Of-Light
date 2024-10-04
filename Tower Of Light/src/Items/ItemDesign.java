package Items;

import Main.GamePanel;
import Tiles.Maps;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static Characters.Characters.getLevel;
import static Items.Usable.usableRandomSelection;
import static Tiles.Tiles.NUMBER_OF_TILES;
import static Tiles.Tiles.TILE_SIZE;

public class ItemDesign {

    final Color ITEM_COLOR = new Color(190, 0, 180); // το χρώμα του πλακιδίου του παίκτη
    final Color ITEM_BORDER_COLOR = Color.BLACK;

    static GamePanel gamePanel;
    Maps map;

    int x, y;

    public static HashMap<String, ArrayList<Item>> itemsMap = new HashMap<>(); //χάρτης με κλειδί τις συντεταγμένες και στοιχεία τη λίστα των αντικειμένων
    static ArrayList<Item> itemsInTile = new ArrayList<>(); //λίστα αντικειμένων σε κάθε πλακίδιο

    public ItemDesign(GamePanel gamePanel, Maps map) {
        ItemDesign.gamePanel = gamePanel;
        this.map = map;
        itemPlacement(new UsableItems.HealthPotion());
        itemPlacement(new UsableItems.MagicJuice());
        itemPlacement(new UsableItems.ManaPotion());
        itemPlacement(new UsableItems.Shield());
        itemPlacement(WeaponsLevel.weaponRandomSelection("LEVEL" + getLevel()));
        gamePanel.repaint();
    }

    public void itemPlacement(Item item) {
        Random rand = new Random();

        x = rand.nextInt(52) * TILE_SIZE;
        y = rand.nextInt(52) * TILE_SIZE;
        do {
            x = rand.nextInt(52) * TILE_SIZE;
            y = rand.nextInt(52) * TILE_SIZE;
        // έλεγχος για να μην τοποθετείται αντικείμενο σε τούβλο και στην είσοδο ή την έξοδο του χάρτη
        }while ((!map.canMove(x / TILE_SIZE , y / TILE_SIZE )) || ((x == TILE_SIZE) && (y == (NUMBER_OF_TILES - 2) * TILE_SIZE)) || ((x == (NUMBER_OF_TILES - 2)) && (y == TILE_SIZE)));
        addItemInMap(x, y, item); // προσθέτω το αντικέιμενο στον χάρτη
        //System.out.println(item);
    }

    public static void itemPlacement(int x, int y, int randomPlacement) {
        if (randomPlacement == 1){
            addItemInMap(x, y, WeaponsLevel.weaponRandomSelection("LEVEL" + getLevel())); // προσθέτω ένα τυχαίο όπλο
        }else {
            addItemInMap(x, y, usableRandomSelection()); // προσθέτω ένα τυχαίο όπλο

        }
    }

    //κάθε φορά ζωγραφίζει όλα τα αντικείμενα του χάρτη
    public void draw(Graphics2D g2) {
        int playerX = gamePanel.playerControl.getPlayerX();
        int playerY = gamePanel.playerControl.getPlayerY();
        int visibilityRadius = gamePanel.playerControl.VISIBILITY_RADIUS;

        for (String key : itemsMap.keySet()) {
            int itemX = Integer.parseInt(key.split(",")[0]);
            int itemY = Integer.parseInt(key.split(",")[1]);

            int distanceX = Math.abs(itemX - playerX);
            int distanceY = Math.abs(itemY - playerY);

            //τα αντικειμενα που είναι εκτος πεδίου δεν σχεδιάζονται
            if (distanceX <= visibilityRadius && distanceY <= visibilityRadius) {
                int x = itemX * TILE_SIZE;
                int y = itemY * TILE_SIZE;
                g2.setColor(ITEM_BORDER_COLOR);
                g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                g2.setColor(ITEM_COLOR);
                g2.fillRect(x + 1, y + 1, TILE_SIZE - 2, TILE_SIZE - 2);
            }
        }
    }

    static void addItemInMap(int x, int y, Item item){
        x = x / TILE_SIZE;
        y = y/TILE_SIZE;
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        if (!itemsMap.containsKey(key)) { // αν ο χάρτης δεν περιέχει τις συγκεκριμένες συντεταγμένες
            itemsMap.put(key, new ArrayList<>()); //δημιουργία νέας λίστας για τις συγκεκριμένες συντεταγμένες
        }
        itemsMap.get(key).add(item); //προσθέτει στη λίστα το συγκεκριμένο αντικείμενο
        gamePanel.repaint();
    }

    //ελέγχει αν στο πλακίδιο υπάρχει αντικείμενο
    boolean tileCheck(int x, int y){
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        return itemsMap.containsKey(key);
    }

    //επιστρέφει τη λίστα με τα αντικέιμενα που ειναι στο πλακίδιο
    ArrayList<Item> getItemsInTile (int x, int y){
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        return itemsMap.get(key);
    }

    //καταργεί από τον χάρτη πλακίδιο χωρίς αντικείμενα
    void removeTile(int x, int y, Item item){
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        itemsInTile = itemsMap.get(key);
        if (itemsInTile.isEmpty()){
            itemsMap.remove(key);
        }
        gamePanel.repaint();
    }

    //διαγραφεί αντικείμενο απο τη λίστα
    void removeItem (int x, int y, Item item){
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        itemsInTile = itemsMap.get(key);
        itemsInTile.remove(item);
        removeTile(x,y,item);
    }

    //μεθοδος που ελεγχει εάν υπάρχουν αντικείμενα στο πλακίδιο
    public static void itemsInTileCheck(int playerX, int playerY){
        for (String key: itemsMap.keySet() ){
            int itemX = Integer.parseInt(key.split(",")[0]) ;
            int itemY = Integer.parseInt(key.split(",")[1]) ;
            if (playerX == itemX && playerY == itemY){
                itemsInTile = itemsMap.get(key);
                for (Item item: itemsInTile){
                    gamePanel.logMessage("There is a " + item.getName() + "." + " " + item.getDescription());
                }
            }
        }
    }

    public static void removeItemFromMap(int itemX, int itemY){
        itemsMap.remove(itemX + "," +itemY);
    }



}
