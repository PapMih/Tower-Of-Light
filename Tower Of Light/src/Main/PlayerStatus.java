package Main;

import Characters.Characters;
import Characters.Player;

import javax.swing.*;

public class PlayerStatus extends JLabel {

    StringBuilder pstate = new StringBuilder("<html><div style='text-align: right;'>");

    public PlayerStatus(Player player) {
        // Πληροφορίες κλάσης και φυλής του παίκτη (χρησιμοποιούμε <p> για καλύτερη στοίχιση)
        pstate.append("<p>")
                .append(player.getPlayerClass())
                .append(" / ")
                .append(player.getPlayerRace())
                .append("</p>");

        // Επίπεδο και πόντοι εμπειρίας
        pstate.append("<p>")
                .append(Characters.getLevel())
                .append(" / ")
                .append(player.getExperiencePoints())
                .append("</p>");

        // Διαχωριστική γραμμή
        pstate.append("<hr/>");

        // Πληροφορίες για τα Hit Points και Mana Points
        pstate.append("<p>HP: ")
                .append(player.getHitPoints())
                .append(" / ")
                .append(player.getMaxHitPoints())
                .append("</p>");

        pstate.append("<p>MP: ")
                .append(player.getManaPoints())
                .append(" / ")
                .append(player.getMaxManaPoints())
                .append("</p>");

        // Δύναμη και νοημοσύνη του παίκτη
        pstate.append("<p>Str: ")
                .append(player.getStrength())
                .append("</p>");

        pstate.append("<p>Int: ")
                .append(player.getIntellect())
                .append("</p>");

        // Διαχωριστική γραμμή
        pstate.append("<hr/>");

        // Εμφάνιση εξοπλισμένων αντικειμένων
        pstate.append("<p>Main Hand: ")
                .append(player.getMainHandName(1)) //εβαλα το int i για να ξεχωρίζει από αυτή που επιστρέφει Weapon, το 1 δεν παιζει καποιο ρόλο
                .append("</p>")
                .append("<p>")
                .append(player.getMainHandDescription())
                .append("</p>");

        if (player.offHand != null){
            pstate.append("<p>Off Hand: ")
                    .append(player.getOffHandName())
                    .append("</p>")
                    .append("<p>")
                    .append(player.getOffHandDescription())
                    .append("</p>");
        } else {
            pstate.append("<p>Off Hand: -</p>");
        }

        if (player.trinket != null){
            pstate.append("<p>Trinket: ")
                    .append(player.getTrinketName())
                    .append("</p>")
                    .append("<p>")
                    .append(player.getTrinkeDescription())
                    .append("</p>")
                    .append("<p>")
                    .append("Remain: ")
                    .append(player.trinket.getRemain())
                    .append("</p>");
        } else {
            pstate.append("<p>Trinket: -</p>");
        }

        // Διαχωριστική γραμμή
        pstate.append("<hr/>");

        // Κλείσιμο του HTML tag
        pstate.append("</div></html>");

        // Ενημέρωση της ετικέτας με το κείμενο
        setText(pstate.toString());
    }
}


