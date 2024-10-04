package Items;

import java.util.ArrayList;
import java.util.HashMap;

import static Tiles.Tiles.TILE_SIZE;

public class ItemHandler {

    static HashMap<String, ArrayList<Item>> map = new HashMap<>(); //χάρτης με κλειδί τις συντεταγμένες και στοιχεία τη λίστα των αντικειμένων
    ArrayList<Item> itemsInTile = new ArrayList<>();

    ItemHandler(int x, int y, Item item){
        x = x / TILE_SIZE;
        y = y/TILE_SIZE;
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        if (!map.containsKey(key)) { // αν ο χάρτης δεν περιέχει τις συγκεκριμένες συντεταγμένες
            this.itemsInTile = new ArrayList<>(); //δημιουργία λίστας αντικειμένων για το συγκεκριμένο πλακίδιο
            this.itemsInTile.add(item); //πρόσθεση του αντικειμένου στη λίστα
            map.put(key, this.itemsInTile); //πρόσθεση του κλειδιού και της λίστας στον χάρτη
        } else {// αν ο χάρτης περιέχει τις συντεταγμένες δεν δημιουργώ νέα λίστα
            this.itemsInTile = map.get(key); //βρίσκει τη λίστα που αναφέρεται στο συγκεκριμένο κλειδί
            this.itemsInTile.add(item); //προσθέτει στη λίστα το συγκεκριμένο αντικείμενο
        }
    }

    //ελέγχει αν στο πλακίδιο υπάρχει αντικείμενο
    boolean tileCheck(int x, int y){
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        return map.containsKey(key);
    }

    //επιστρέφει τη λίστα με τα αντικέιμενα που ειναι στο πλακίδιο
    ArrayList<Item> getItemsInTile (int x, int y){
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        return map.get(key);
    }

    //καταργεί από τον χάρτη πλακίδιο χωρίς αντικείμενα
    void removeTile(int x, int y, Item item){
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        this.itemsInTile = map.get(key);
        if (this.itemsInTile.isEmpty()){
            map.remove(key);
        }
    }

    //διαγραφεί αντικείμενο απο τη λίστα
    void removeItem (int x, int y, Item item){
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        this.itemsInTile = map.get(key);
        this.itemsInTile.remove(item);
        removeTile(x,y,item);
    }

    //προσθέτει αντικείμενο στη λίστα
    void addItem (int x, int y, Item item){
        String key = x + "," + y; // μετατροπή των συντεταγμένων σε String
        this.itemsInTile = map.get(key);
        this.itemsInTile.add(item);
    }






}
