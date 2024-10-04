package Main;

import Characters.Klass;
import Characters.PlayerRace;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TitlePanel extends JPanel {
    private static PlayerRace playerRace; // επιλεγμένη φυλή
    private static Klass playerKlass; // επιλεγμένη κλάση

    public TitlePanel() {
        // ρύθμιση του layout του panel
        setLayout(new BorderLayout());

        // δημιουργία του panel που θα εμφανίζει τον τίτλο του παιχνιδιού
        JPanel textPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setFont(new Font("Algerian", Font.BOLD, 44)); //επιλογή γραμματοσειράς
                String text = "Tower of Light"; //κείμενο
                FontMetrics metrics = g.getFontMetrics(g.getFont()); //μετρηση διαστάσεων κειμένου για την στοίχιση του
                int x = (getWidth() - metrics.stringWidth(text)) / 2; //στοίχηση κειμένου
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent(); //στοίχηση κειμένου
                setBackground(new Color(100, 50, 58)); //επιλογή χρώματος φόντου
                g2.setColor(Color.WHITE);// χρώμα κειμένου
                g2.drawString(text, x, y);
            }
        };
        textPanel.setPreferredSize(new Dimension(600, 100)); // διαστάσεις textPanel
        add(textPanel, BorderLayout.NORTH); //προσθήκη του textPanel στο πάνω μέρος του TitlePanel

        // δημιουργία panel για την προσθήκη των λιστών με τη φυλή και την κλάση του παίχτη
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(1, 2, 10, 10)); // το listPanel αποτελείται απο 1 γραμμή και 2 στήλες

        //  λίστα για τη φυλή
        JList<PlayerRace> raceList = new JList<>(PlayerRace.values()); //λίστα με δεδομένα τύπου PlayerRace που περιέχει όλες τις τιμές
        configureList(raceList, "Choose Race"); // κλήση μεθόδου δημιουργίας λίστας
        raceList.addListSelectionListener(e -> playerRace = raceList.getSelectedValue()); //προσθήκη στην μεταβλήτη playerRace της επιλεγμένης φυλής με χρήση lamda
        listPanel.add(raceList); //προσθήκη της λίστας στο panel

        //  λίστα για τη κλάση
        JList<Klass> klassList = new JList<>(Klass.values()); //λίστα με δεδομένα τύπου PlayerKlass που περιέχει όλες τις τιμές
        configureList(klassList, "Choose Class"); // κλήση μεθόδου δημιουργίας λίστας
        klassList.addListSelectionListener(e -> playerKlass = klassList.getSelectedValue()); //προσθήκη στην μεταβλήτη playerKlass της επιλεγμένης φυλής με χρήση lamda
        listPanel.add(klassList);//προσθήκη της λίστας στο panel

        add(listPanel, BorderLayout.CENTER); //τοποθέτηση του listPanel στο κέντρο

        // δημιουργία panel για τα κουμπιά "Start Game" και "Instructions"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10)); // διαχωρισμός σε δύο κουμπιά

        // δημιουργία κουμπιού Start Game
        JButton startButton = new JButton("Start Game"); //δημιουργία κουμπιού
        startButton.setFont(new Font("Algerian", Font.BOLD, 24)); //επιλογή γραμματοσειράς
        startButton.addActionListener(e -> { //ρύθμιση κουμπιού με χρήση lamda
            if (playerRace != null && playerKlass != null) {
                Main.setGameState(1); // αν έχει επιλεγεί φυλή και κλάση αλλάζει η κατάσταση του παιχνιδιού
            } else {
                JOptionPane.showMessageDialog(null, "Please select a race and class!"); // εμφάνιση μηνύματος αν πατήσει το κουμπί χωρίς να ε΄χει επιλέξει φυλή και κλάση
            }
        });
        buttonPanel.add(startButton); // Προσθήκη του κουμπιού στο panel

        // δημιουργία κουμπιού Instructions
        JButton instructionsButton = new JButton("Instructions"); // δημιουργία κουμπιού για οδηγίες
        instructionsButton.setFont(new Font("Algerian", Font.BOLD, 24)); // επιλογή γραμματοσειράς
        instructionsButton.addActionListener(e -> { // ρύθμιση κουμπιού με χρήση lamda
            try {
                // άνοιγμα του αρχείου ODHGIES.txt με το Notepad
                Runtime.getRuntime().exec("notepad.exe ./src/Main/ODHGIES.txt");
            } catch (Exception ex) {
                ex.printStackTrace(); // εμφάνιση σφάλματος στην κονσόλα σε περίπτωση αποτυχίας
            }
        });
        buttonPanel.add(instructionsButton); // Προσθήκη του κουμπιού στο panel

        add(buttonPanel, BorderLayout.SOUTH); // τοποθέτηση του buttonPanel κάτω, κεντραρισμένα
    }

    // μέθοδος για τη δημιουργία των λιστών
    private <T> void configureList(JList<T> list, String title) {
        list.setVisibleRowCount(4); //ρύθμιση του πόσα αντικείμενα θα φαίνονται
        list.setBackground(new Color(100, 50, 58)); //επιλογή χρώματος φόντου
        list.setFont(new Font("Algerian", Font.BOLD, 14)); //επιλογή γραμματοσειράς
        list.setForeground(Color.WHITE);// χρώμα κειμένου περιεχομένων λιστών
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleColor(Color.WHITE); // χρώμα κειμένου τίτλου λιστών
        list.setBorder(border); //προσθήκη του τίτλου
    }

    // μέθοδοι για την επιστροφή της επιλεγόμενης φυλής
    public static PlayerRace getPlayerRace() {
        return playerRace;
    }

    // μέθοδοι για την επιστροφή της επιλεγόμενης κλάσης
    public static Klass getPlayerKlass() {
        return playerKlass;
    }
}
