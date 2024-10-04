package Characters;

import java.util.List;
import java.util.Random;

public enum EnemiesLevel {  LEVEL1(List.of(new Enemies.Priest())),
    LEVEL2(List.of(new Enemies.Priest(), new Enemies.Knight(), new Enemies.Bishop())),
    LEVEL3(List.of(new Enemies.Knight(), new Enemies.Bishop(), new Enemies.Paladin())),
    LEVEL4(List.of(new Enemies.Knight(), new Enemies.Bishop(), new Enemies.Paladin(), new Enemies.Archangel())),
    LEVEL5(List.of(new Enemies.Archangel(), new Enemies.Paladin())),
    LEVEL6(List.of(new Enemies.Archangel(), new Enemies.HeraldOfLight()))
;

    final List<Enemies> enemies;


EnemiesLevel(List<Enemies> enemies) {
        this.enemies = enemies;
    }

    //μέθοδος για την επιλογή τυχαίου όπλου ανά επίπεδο
    public static Enemies randomSelection(String level){
        EnemiesLevel enemiesLevel = EnemiesLevel.valueOf(level); //μετατροπή του string σε τιμή του enum
        int size = enemiesLevel.enemies.size(); //το μέγεθος του
        Random random = new Random();
        int randomInt = random.nextInt(size); //επιλέγω τυχαίο στοιχείο από τη λίστα
        Enemies selectedEnemy = enemiesLevel.enemies.get(randomInt); // επιλέγω τον τυχαίο εχθρό

        Enemies newEnemy = null; //δημιουργία νέου εχθρού

        //ανάλογος την τυχαία επιλογή φτιάχνω αντίστοιχο εχθρό
        if (selectedEnemy instanceof Enemies.Priest) {
            newEnemy = new Enemies.Priest();
        } else if (selectedEnemy instanceof Enemies.Knight) {
            newEnemy = new Enemies.Knight();
        } else if (selectedEnemy instanceof Enemies.Bishop) {
            newEnemy = new Enemies.Bishop();
        } else if (selectedEnemy instanceof Enemies.Paladin) {
            newEnemy = new Enemies.Paladin();
        } else if (selectedEnemy instanceof Enemies.Archangel) {
            newEnemy = new Enemies.Archangel();
        } else if (selectedEnemy instanceof Enemies.HeraldOfLight) {
            newEnemy = new Enemies.HeraldOfLight();
        }

        return newEnemy;
    }
}
