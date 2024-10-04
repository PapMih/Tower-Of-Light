package Characters;

import Items.Weapons;
import Main.GamePanel;

import static Items.Equippable.getDamage;
import static Tiles.Tiles.TILE_SIZE;

public class EnemiesAttack {

    EnemiesAttack(){}

    public static void enemiesAttack(int playerX, int playerY, EnemiesDesign enemiesDesign, Player player, GamePanel gamePanel){ //για όλους τους εχθρούς
        enemiesDesign.enemiesMovement(playerX, playerY);
        //System.out.println("Player X: " + playerX + " Player Y: " + playerY);
        for (String key : EnemiesDesign.enemiesMap.keySet()) {
            int enemyX = Integer.parseInt(key.split(",")[0]) * TILE_SIZE;
            int enemyY = Integer.parseInt(key.split(",")[1]) * TILE_SIZE;
            //System.out.println("Enemy X: " + enemyX + " Enemy Y: " + enemyY);
            int distanceX = enemyX - playerX * TILE_SIZE;
            int distanceY = enemyY - playerY * TILE_SIZE;
            //System.out.println("Distance X: " + distanceX + " Distance Y: " + distanceY);
            int[] weaponDamage = new int[3]; //πινακας για τις τιμές ανά είδος επίθεσης
            int[] playerDefense = new int[3]; //πίνακας με τις τιμές αμυνας ανα είδος επίθεσης
            int[] playerDamage = new int[3]; //πίνακας με το damage αν είδος επίθεσης
            int finalDamage = 0 ; //το τελικό ποσό ζημιάς

            if (Math.abs(distanceX) <= TILE_SIZE && Math.abs(distanceY) <= TILE_SIZE ) { // εάν ο εχθρός είναι σε διπλανό πλακίδιο από τον παίχτη
                //System.out.println("Ελεγχος");
                finalDamage = 0; //μηδενισμός της ζημιάς για τον υπολογισμό εκ νέου από τον επόμενο αντίπαλο
                weaponDamage = getDamage(EnemiesDesign.enemiesMap.get(key).weapon); //"ρίξιμο" ζαριού
                playerDefense = player.getDef();
                for(int i = 0 ; i <= 2 ; i++){
                    playerDamage[i] = weaponDamage[i] - playerDefense[i];//η ζημιά στον παίχτη από κάθε είδος επίθεσης είναι η ζημιά από το όπλο μείον την άμυνα
                    //System.out.println(playerDamage[i]);
                    //System.out.println(weaponDamage[i]);
                    //System.out.println(playerDefense[i]);
                    if(playerDamage[i] < 0){ // σε περίπτωση που η ζημιά είναι αρνητική τότε την κάνω μηδέν
                        playerDamage[i] = 0;
                    }
                    finalDamage = finalDamage + playerDamage[i];
                }
                player.hitPointReduce(finalDamage);
                // καταγραφή της επίθεσης στο Game Log
                gamePanel.logMessage("You have been attacked by a " +  EnemiesDesign.enemiesMap.get(key).name + ". Amount of damage: " + finalDamage );
            }
        }
    }
}
