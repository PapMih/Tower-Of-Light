package Main;

import Characters.EnemiesDesign;
import Characters.Player;
import Characters.PlayerControl;
import Items.ItemDesign;
import Tiles.Maps;

import javax.swing.*;
import java.awt.*;


import static Main.TitlePanel.getPlayerKlass;
import static Main.TitlePanel.getPlayerRace;
import static Tiles.Tiles.NUMBER_OF_TILES;
import static Tiles.Tiles.TILE_SIZE;

public class GamePanel extends JPanel {

    GameHandler gHandler = new GameHandler(this); //δημιουργία αντικειμένου για τον χειρισμό
    Maps map = new Maps(this);
    public EnemiesDesign enemies = new EnemiesDesign(this, map);
    public PlayerControl playerControl = new PlayerControl(this, gHandler, map, enemies);
    ItemDesign item = new ItemDesign(this, map);
    public Player player = new Player(getPlayerRace(), getPlayerKlass());
    PlayerStatus playerStatus;

    JTextArea statusText = new JTextArea(8, 80); //δημιουργία του Game Log
    JScrollPane jsb = new JScrollPane(statusText); // προσθήκη του JScrollPane για την κύλιση και προσθηκη σε αυτό του statusText

    public GamePanel(){
        this.setPreferredSize(new Dimension(((NUMBER_OF_TILES + 9) * TILE_SIZE) , (NUMBER_OF_TILES + 8) * TILE_SIZE));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.gray);
        this.addKeyListener(gHandler); //προσθήκη του Key Listener
        this.setFocusable(true); //για να μπορει το Panel να λαμβάνει απο το πληκτρολόγιο
        playerStatus = new PlayerStatus(player); // αρχικοποίηση του PlayerStatus
        this.add(playerStatus, BorderLayout.EAST); // προσθήκη του PlayerStatus στα δεξιά
        statusText.setEditable(false);//το statusText δεν μπορεί να επεξεργαστεί
        this.add(jsb, BorderLayout.SOUTH); //τοποθέτηση του scroll pane στα "νοτια"
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; //αλλαγή τύπου ώστε να μπορώ να χρησιμοποιήσω μεθόδους της Grafics2D
        map.draw(g2);
        item.draw(g2);
        enemies.draw(g2);
        playerControl.draw(g2);
    }

    //μεθοδος για την ενημέρωση του playerStatus
    public void updatePlayerStatus() {
        this.remove(playerStatus); // αφαίρεση του παλιού PlayerStatus
        playerStatus = new PlayerStatus(player); // Δημιουργία νέου playerStatus
        this.add(playerStatus, BorderLayout.EAST); // προσθήκη του PlayerStatus στα δεξιά
        this.revalidate(); // Επικύρωση της διάταξης
        this.repaint(); // Ανασχεδίαση του panel
    }

    //μεθοδος για την καταγραφή των μηνυμάτων
    public void logMessage(String message) {
        statusText.append(message + "\n"); //προσθήκη του μηνύματος στο status Text
        statusText.setCaretPosition(statusText.getDocument().getLength()); // μετακίνηση της μπάρας κύλισης στο τέλος
    }
}
