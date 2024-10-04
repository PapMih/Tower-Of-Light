package Items;

import Characters.Player;

import java.util.Arrays;
import java.util.List;

import static Characters.Player.*;
import static Items.ItemDesign.gamePanel;

//έφτιαξα μια γενικη κλαση UsableItems και inner class τα εκαστοτε αντικειμενα ώστε να είναι πιο μαζεμένα

public abstract class UsableItems extends Usable{
    public boolean isBonusApplied;

    UsableItems(String name, String description, List<EffectType> effects, List<SlotType> slots) {
        super(name, description, effects, slots);
        isBonusApplied = false;
    }

    public static class HealthPotion extends UsableItems{//static κλάσεις ώστε να μπορώ να δημιουργήσω αντικείμενο της εσωτερικής χωρίς να χρειάζεται να δημιουργήσω στην εξωτερικη κλαση
       public ItemEffect hpReplenish;
       int useAmount;

        public HealthPotion() {
            super("Health Potion", "Hit points: +40", Arrays.asList(EffectType.HP_REPLENISH), Arrays.asList(SlotType.TRINKET));
            this.hpReplenish = new ItemEffect(EffectType.HP_REPLENISH, 40,40);
            this.useAmount = 5 ; //καθε φορα αυξάνονται κατά 5 τα Hit Points και μειώνεται το remain
        }

        @Override
        public void use() {
            if(Player.hitPointIncrease(useAmount)) {
                hpReplenish.remain -= useAmount; //μείωση μόνο αν αυξήθηκε το health potion
                gamePanel.logMessage("Health Potion used. Amount: 5.");
                if (hpReplenish.getRemain() <= 0) {//αν εγινε μηδεν αφαιρώ το αντικείμενο
                    Player.removeTrinket();
                }
            }
        }

        @Override
        public int getRemain() {
            return hpReplenish.remain; // Μόνο επιστρέφει την τιμή χωρίς μείωση
        }
    }

    public static class ManaPotion extends UsableItems{//static κλάσεις ώστε να μπορώ να δημιουργήσω αντικείμενο της εσωτερικής χωρίς να χρειάζεται να δημιουργήσω στην εξωτερικη κλαση
        ItemEffect mpReplenish;
        int useAmount;

        public ManaPotion() {
            super("Mana Potion", "Mana points: +40", Arrays.asList(EffectType.MP_REPLENISH), Arrays.asList(SlotType.TRINKET));
            this.mpReplenish = new ItemEffect(EffectType.MP_REPLENISH, 40,40);
            this.useAmount = 5 ; //καθε φορα αυξάνονται κατά 5 τα Mana Points και μειώνεται το remain
        }

        @Override
        public void use() {
            if (Player.manaPointIncrease(useAmount)){
                mpReplenish.remain -= useAmount; //μείωση μόνο αν αυξήθηκε το health potion
                gamePanel.logMessage("Mana Potion used. Amount: 5.");
                if (mpReplenish.getRemain() <= 0) {//αν εγινε μηδεν αφαιρώ το αντικείμενο
                    Player.removeTrinket();
                }
            }
        }

        @Override
        public int getRemain() {
            return mpReplenish.remain;
        }
    }

    public static class MagicJuice extends UsableItems{//static κλάσεις ώστε να μπορώ να δημιουργήσω αντικείμενο της εσωτερικής χωρίς να χρειάζεται να δημιουργήσω στην εξωτερικη κλαση
        ItemEffect strBonus;
        public boolean isBonusApplied = false;  // σημαία με σκοπό να μην εφαρμόζεται σε κάθε γύρο χρήσης η πρόσθεση του bonus

        public MagicJuice() {
            super("Magic Juice", "Strength Bonus: +5", Arrays.asList(EffectType.STR_BONUS), Arrays.asList(SlotType.TRINKET));
            this.strBonus = new ItemEffect(EffectType.STR_BONUS, 5, 10);
        }

        @Override
        public void use() {
            if (!isBonusApplied) {
                bonusStrength(strBonus.getAmount());  // εφαρμόζεται το bonus μόνο αν δεν έχει ήδη εφαρμοστεί
                isBonusApplied = true;  // αλλαγή της σημαίας
            }
        }

        @Override
        public int getRemain() {
            return Player.counter;
        }
    }

    public static class Shield extends UsableItems{//static κλάσεις ώστε να μπορώ να δημιουργήσω αντικείμενο της εσωτερικής χωρίς να χρειάζεται να δημιουργήσω στην εξωτερικη κλαση
        ItemEffect dfBonus;

        public Shield() {
            super("Shield", "Defence Bonus: +5", Arrays.asList(EffectType.DF_BONUS), Arrays.asList(SlotType.TRINKET));
            this.dfBonus = new ItemEffect(EffectType.DF_BONUS, 5,10);
        }

        @Override
        public void use() {
                if (!isBonusApplied) {
                    bonusDefence(dfBonus.getAmount());  // εφαρμόζεται το bonus μόνο αν δεν έχει ήδη εφαρμοστεί
                    isBonusApplied = true;  // αλλαγή της σημαίας
                }
        }

        @Override
        public int getRemain() {
            return Player.counter;
        }
    }
}
