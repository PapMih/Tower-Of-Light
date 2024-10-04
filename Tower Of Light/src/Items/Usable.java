package Items;

import java.util.List;
import java.util.Random;

public abstract class Usable extends Item {

    Usable(String name, String description, List<EffectType> effects, List<SlotType> slots) {
        super(name, description, effects, slots );
    }

    public abstract void use();
    public abstract int getRemain();

    public String getUsableName() {
        return name; //επιστρέφει το ονομα
    }

    public String getUsableDescription() {
        return description; //επιστρέφει το ονομα
    }

    public static Usable usableRandomSelection(){
        Random random = new Random();
        int randomInt = random.nextInt(4); //επιλέγω τυχαίο στοιχείο
        if (randomInt ==0) { return new UsableItems.HealthPotion();}
        else if (randomInt == 1) {return new UsableItems.Shield();}
        else if (randomInt == 2) {return new UsableItems.ManaPotion();}
        else {{return new UsableItems.MagicJuice();}
        }


    }

}
