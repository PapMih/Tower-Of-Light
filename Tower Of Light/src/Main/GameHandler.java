package Main;

import Characters.EnemiesAttack;
import Characters.Player;
import Items.ItemDesign;
import Items.UsableItems;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Characters.Characters.levelCalc;
import static Characters.Player.checkMagicJuiceShield;
import static Characters.PlayerControl.playerCollectItem;
import static Tiles.Tiles.TILE_SIZE;

public class GameHandler implements KeyListener {
    private final GamePanel gamePanel;

    public GameHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        switch (c) {
            //καθε φορα που εκτελει ο παιχτης κίνηση εκτελείται η enemiesAttack με την οποια οι εχθροι που βρίσκονται σε κοντινη αποσταση μπορουν να επιτεθουν και ανανεώνεται
            // Player Status
            case 'a', 'A', 'α', 'Α':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinke Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                gamePanel.playerControl.movePlayerLeft();
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 'd', 'D', 'δ', 'Δ':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinke Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                gamePanel.playerControl.movePlayerRight();
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 'w', 'W', 'ς':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinke Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                gamePanel.playerControl.movePlayerUp();
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 's', 'S', 'σ', 'Σ':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinket Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                gamePanel.playerControl.movePlayerDown();
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case ' ':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinket Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                gamePanel.playerControl.playerWeaponAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies);
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 'T', 't', 'τ', 'Τ':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinket Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                playerCollectItem(1, gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY());
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 'Υ', 'υ', 'Y', 'y':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinket Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                playerCollectItem(2, gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY());
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 'u', 'U', 'θ', 'Θ':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinket Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                playerCollectItem(3, gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY());
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 'μ', 'm', 'Μ', 'M':
                //ελεγχος αν υπάρχει ManaPotion
                if (Player.trinket instanceof UsableItems.ManaPotion) {
                    Player.trinket.use();
                } else {
                    gamePanel.logMessage("There is no Mana Potion in trinket" );
                }
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 'η', 'Η', 'h', 'H':
                //ελεγχος αν υπάρχει HealthPotion
                if (Player.trinket instanceof UsableItems.HealthPotion) {
                    Player.trinket.use();
                } else {
                    gamePanel.logMessage("There is no Health Potion in trinket" );
                }
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 'r', 'ρ', 'Ρ', 'R':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinke Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                gamePanel.playerControl.restoration();
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 'χ', 'x', 'X', 'Χ':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinket Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                gamePanel.playerControl.playerSpellAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies);
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;

            case 'L', 'l', 'Λ', 'λ':
                checkMagicJuiceShield(); //ελεγχος αν υπαρχει στο trinket Magic Juice ή Shield ωστε να μειωθούν οι γυροι για τους οποιους ισχυουν τα αντικειμενα
                gamePanel.playerControl.castBeaconSpell();
                EnemiesAttack.enemiesAttack(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY(), gamePanel.enemies, gamePanel.player, gamePanel);
                levelCalc(gamePanel.player.getPlayerXp()); // σε κάθε βήμα υπολογίζω το level
                gamePanel.playerControl.reduceBeaconCooldown();//μείωση cooldown
                gamePanel.updatePlayerStatus();
                ItemDesign.itemsInTileCheck(gamePanel.playerControl.getPlayerX(), gamePanel.playerControl.getPlayerY()); // Προσθήκη κλήσης για έλεγχο αντικειμένων
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
