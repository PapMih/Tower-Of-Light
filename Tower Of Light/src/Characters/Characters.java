package Characters;

public class Characters {

    int x, y;
    static int level = 1; //αρχικοποίηση πριν τον constructor για να υπάρχει η τιμή όταν θα δημιουργηθεί ο παίχτης

    Characters(){}

    //μεθοδος που υπολογίζω το level
    public static void levelCalc(int xp) {
        if (xp >= 0 && xp <= 299) {
            level = 1;
        } else if (xp >= 300 && xp <= 899) {
            level = 2;
        } else if (xp >= 900 && xp <= 2699) {
            level = 3;
        } else if (xp >= 2700 && xp <= 6499) {
            level = 4;
        } else if (xp >= 6500 && xp <= 13999) {
            level = 5;
        } else if (xp >= 14000) {
            level = 6;
        }
    }

    public static int  getLevel(){
        return level;
    }




}
