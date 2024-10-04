package Characters;

public enum PlayerKlass {ChaosKnight (new int[][] {{10,0,2,0}, {15,0,4,0}, {20,0,6,0}, {25,0,8,0}, {30,0,10,0}, {40,0,12,0}}),
    Necromancer(new int[][]{{10, 0, 2, 0}, {15, 0, 4, 0}, {20, 0, 6, 0}, {25, 0, 8, 0}, {30, 0, 10, 0}, {40, 0, 12, 0}}),
    Fallen(new int[][]{{10, 10, 5, 5}, {13, 20, 7, 9}, {16, 30, 9, 13}, {20, 40, 11, 17}, {28, 50, 13, 21}, {40, 700, 15, 25}});

    private int[][] values;

    PlayerKlass(int[][] values) {
        this.values = values;
    }

    //μεθοδος που επιστρέφει τις τιμές
    public int[][] getValues() {
        return values;
    }

    //μέθοδος που επιστρέφει το bonus hp
    public int getBonusHp(int level) {
        return values[level - 1][0];  // οι γραμμες ειναι το level και η πρώτη στήλη το bonus hp
    }

    //μέθοδος που επιστρέφει το bonus mp
    public int getBonusMp(int level) {
        return values[level - 1][1];  // οι γραμμες ειναι το level και η δεύτερη στήλη το bonus mp
    }

    //μέθοδος που επιστρέφει το bonus str
    public int getBonusStr(int level) {
        return values[level - 1][2];  // οι γραμμες ειναι το level και η δεύτερη στήλη το bonus str
    }

    //μέθοδος που επιστρέφει το bonus int
    public int getBonusInt(int level) {
        return values[level - 1][3];  // οι γραμμες ειναι το level και η δεύτερη στήλη το bonus int
    }
}
