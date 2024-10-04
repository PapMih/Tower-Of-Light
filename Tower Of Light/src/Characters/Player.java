package Characters;

import Items.*;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static Characters.PlayerControl.gamePanel;
import static Items.UsableItems.*;

public class Player extends Characters {
    //ο παιχτης ειναι ένας ως εκ τούτου μπορώ να χρησιμοποιώ στατικές μεταβλητές

    public static int beaconCooldown = 0; // μεταβλητή για το cooldown του beacon
    public static List<Point> beaconTiles = new ArrayList<>(); //λίστα με τα πλακίδια των beacons

    PlayerRace race;
    static Klass klass;
    static int str;
    static int intel;
    static int[] def = new int[3];
    static int hp;
    static int mp;
    int xp;
    public static Weapons mainHand;
    public static Weapons offHand;
    public static UsableItems  trinket;
    public static int counter; //μετρητής για το σε πόσους γύρους έχουν χρησιμοποιηθεί τοMagic Juice και το Shield
    public static int beacons;


    public Player(PlayerRace race, Klass klass)  {
        super();
        this.klass = klass;
        this.race = race;
        this.xp = 0;
        mainHand = WeaponsLevel.weaponRandomSelection("LEVEL" + getLevel()); //επιλέγω αρχικά τυχαία ένα όπλο που να αντιστοιχεί στο πρώτο επίπεδο
        offHand = null; //αρχικά δεν έχει όπλο off hand
        trinket = null; //αρχικά δεν έχει όπλο off hand

        // Υπολογισμός των τιμών βάσει του race και του klass
        str = race.getStr(getLevel()) + klass.getBonusStr(getLevel());
        intel = race.getInt(getLevel()) + klass.getBonusInt(getLevel());
        hp = klass.getBonusHp(getLevel());
        mp = klass.getBonusMp(getLevel());

        counter = 10;
        beacons = 0;
    }

    public char[] getPlayerRace() {
        return race.toString().toCharArray(); //επιστρέφει τη φυλή
    }

    public char[] getPlayerClass() {
        return klass.toString().toCharArray(); //επιστρέφει την κλάση
    }

    public char[] getExperiencePoints() {
        String xpString = Integer.toString(xp); //μετατροπή σε string
        return xpString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public char[] getHitPoints() {
        String hpString = Integer.toString(hp); //μετατροπή σε string
        return hpString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public static int getHitPointsInt() {
        return hp;
    }

    public char[] getMaxHitPoints() {
        String maxHpString = Integer.toString(klass.getBonusHp(level)); //μετατροπή σε string
        return maxHpString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public static int getMaxHitPointsInt() {
        return klass.getBonusHp(level);
    }

    public char[] getManaPoints() {
        String mpString = Integer.toString(mp); //μετατροπή σε string
        return mpString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public static int getManaPointsInt() {
        return mp;
    }

    public char[] getMaxManaPoints() {
        String maxMpString = Integer.toString(klass.getBonusMp(level)); //μετατροπή σε string
        return maxMpString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public static int getMaxManaPointsInt() {
        return klass.getBonusMp(level); //μετατροπή σε string
    }

    public char[] getStrength() {
        String strString = Integer.toString(str); //μετατροπή σε string
        return strString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public char[] getIntellect() {
        String intelString = Integer.toString(intel); //μετατροπή σε string
        return intelString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public static int getIntellectInt() {
         return intel;

    }

    public char[] getMainHandName(int i) { //εβαλα το int i για να ξεχωρίζει από αυτή που επιστρέφει Weapon
        String mainHandString = mainHand.getEquippableName(); //μετατροπή σε string
        return mainHandString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public Weapons getMainHandName() {
        return mainHand; // μετατροπή σε πίνακα χαρακτήρων
    }

    public char[] getMainHandDescription() {
        String mainHandString = mainHand.getEquippableDescription(); //μετατροπή σε string
        return mainHandString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public char[] getOffHandName() {
        String offHandString = offHand.getEquippableName(); //μετατροπή σε string
        return offHandString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public char[] getOffHandDescription() {
        String offHandString = offHand.getEquippableDescription(); //μετατροπή σε string
        return offHandString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }


    public char[] getTrinketName() {
        String trinketString = trinket.getUsableName(); //μετατροπή σε string
        return trinketString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public char[] getTrinkeDescription() {
        String trinkeString = trinket.getUsableDescription(); //μετατροπή σε string
        return trinkeString.toCharArray(); // μετατροπή σε πίνακα χαρακτήρων
    }

    public int[] getDef(){
        return def;
    }

    public void hitPointReduce(int finalDamage) {
        //System.out.println(finalDamage);
        hp = hp - finalDamage;
        hpCheck();
    }

    public static boolean hitPointIncrease(int amount) {
        //System.out.println(amount);
        if(getHitPointsInt() + amount <= getMaxHitPointsInt()){
            hp = hp + amount;
            return true;
        }
        else {return false;}
    }

    public static boolean manaPointIncrease(int amount) {
        if(getManaPointsInt() + amount <= getMaxManaPointsInt()){
            mp = mp + amount;
            return true;
        }
        else {return false;}
    }

    private void hpCheck() {
        if (getHitPointsInt() <= 0){
            System. exit(0);
        }
    }

    public static void removeTrinket(){
        trinket = null;
    }

    public static void getItemInMainHand(Weapons weapon){
        mainHand = weapon;
    }

    public static void getItemInOffHand(Weapons weapon){
        offHand = weapon;
    }

    public static void getItemInTrinket(UsableItems item){
        trinket = item;
    }

    //μεθοδος που ελεγχει τι υπαρχει στη θεση trinket και μειώνει τον counter για κάθε γύρο που περνάει με Magic Juice ή Shield
    public static void checkMagicJuiceShield(){
        //αν υπάρχει Magic Juice ή Shield στη θέση trinket και δεν έχουν περάσει 10 γύροι
        if (((trinket instanceof MagicJuice) || (trinket instanceof Shield)) && counter > 1) {
            counter -= 1; //μειώνει τον counter κατά 1
            trinket.use(); //χρησιμοποιεί το αντικείμενο
        } else if (((trinket instanceof MagicJuice) || (trinket instanceof Shield)) && counter == 1) { //όταν φτάσει τους 10 γύρους χρήσης
            if (trinket instanceof MagicJuice){
                StopBonusStrength(5);  // σταματάμε το bonus
            }
            else {StopBonusDefence(5);}

            counter = 10;  // επαναφορά του μετρητή
            ((UsableItems)trinket).isBonusApplied = false;  // επαναφορά της σημαίας του MagicJuice
            trinket = null; //ελευθέρωση της θέσης trinket
        }
    }

    public static void bonusStrength(int amount){
        str += amount;
    }

    public static void StopBonusStrength(int amount){
        str -= amount;
    }

    public static void bonusDefence(int amount){
        for (int i = 0 ; i < 3 ; i++){
            def[i] += amount;
        }
    }

    public static void StopBonusDefence(int amount){
        for (int i = 0 ; i < 3 ; i++){
            def[i] -= amount;
        }
    }

   void increaseXP(int amount){
        xp += amount;
   }

   public int getPlayerXp(){
        return this.xp;
   }

    public static void hitPointIncreaseByRestoration(int amount) {
        //System.out.println(amount);
        if(getHitPointsInt() + amount < getMaxHitPointsInt()){
            hp = hp + amount;
            gamePanel.logMessage("Restoration: Increased Hit points by " + amount + ".");
        }
    }

    public static void manaPointIncreaseByRestoration(int amount) {
        if(getManaPointsInt() + amount < getMaxManaPointsInt()){
            mp = mp + amount;
            gamePanel.logMessage("Restoration: Increased Mana points by " + amount + ".");
        }
    }

    public static void manaPointDecreaseBySpellAttack(int amount) {
            mp = mp - amount;
            gamePanel.logMessage("Spell attack: Decreases Mana points by " + amount + ".");
    }

}

