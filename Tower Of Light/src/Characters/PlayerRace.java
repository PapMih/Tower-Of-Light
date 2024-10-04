package Characters;

public enum PlayerRace {Orcs(new int[]{10, 8, 1, 1, 0}),
    Taurens(new int[]{12, 6, 1, 2, 0}),
    Humans(new int[]{9, 9, 1, 1, 1}),
    Elfs(new int[]{6, 12, 0, 1, 2});

    private int[] values;

    PlayerRace(int[] values) {
        this.values = values;
    }

    //μεθοδος που επιστρέφει τις τιμές
    public int[] getValues() {
        return values;
    }

    //μέθοδος που επιστρέφει το strength
    public int getStr(int level) {
        return values[0];
    }

    //μέθοδος που επιστρέφει το intellect
    public int getInt(int level) {
        return values[1];  //
    }

    //μέθοδος που επιστρέφει την αμυνα σε swing
    public int getSwing(int level) {
        return values[2];
    }

    //μέθοδος που επιστρέφει την αμυνα σε thrust
    public int getThrust(int level) {
        return values[3];
    }

    //μέθοδος που επιστρέφει την αμυνα σε magical
    public int getMagic(int level) { return values[4];
    }
}