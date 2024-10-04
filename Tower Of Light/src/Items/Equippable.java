package Items;

import java.util.List;
import java.util.Random;

public abstract class Equippable extends Item {

    //πίνακες με τα στοιχεία του "ζαριού" του κάθε όπλου ανά τύπο επίθεσης
    int[] swing;
    int[] thrust;
    int[] magical;

    Equippable(String name, String description, List<EffectType> effects, List<SlotType> slots, int[] swing, int[] thrust, int[] magical) {
        super(name, description, effects, slots);
        this.swing = swing;
        this.thrust = thrust;
        this.magical = magical;
    }

    public String getEquippableName() {
        return name; //επιστρέφει το ονομα
    }

    public String getEquippableDescription() {
        return description; //επιστρέφει το ονομα
    }


    public int[] getSwing() {
        return swing; //επιστρέφει τους συντελεστές για τη ρίψη ζαριού για την επίθεση τύπου Swing
    }

    public int[] getThrust() { //επιστρέφει τους συντελεστές για τη ρίψη ζαριού για την επίθεση τύπου thrust
        return thrust;
    }

    public int[] getMagical() { //επιστρέφει τους συντελεστές για τη ρίψη ζαριού για την επίθεση τύπου Magical
        return magical;
    }

    public int hasEffect(Equippable weapon) {
        if (weapon.effects.contains(EffectType.NONE)) {
            return 0;
        } else {
            return 1;
        }
    }

    //μέθοδος που επιστρέφει το damage απο το όπλο
    public static int[] getDamage(Weapons weapon) {
        int [] damage = new int[3]; // στοιχείο 0 για swing, 1 για thurst και 2 για magical
        Random random = new Random();

        if (weapon.swing[0] == 0){
            damage[0] = 0; //το swing είναι μηδέν
        }else if(weapon.swing[0] == 1){
            damage[0] =   (random.nextInt(weapon.swing[1]) + 1) + weapon.swing[2]; //ρίχνει ένα ζάρι με πλευρές όσες το στοιχείο 1 του swing και προσθέτει την τρίτη τιμή
        }else {
            damage[0] =   (random.nextInt(weapon.swing[0]) + 1) + (random.nextInt(weapon.swing[0]) + 1) + weapon.swing[2]; //ρίχνει δυο ζάρια με πλευρές όσες το στοιχείο 1 του swing
            // και προσθέτει την τρίτη τιμή
        }

        if (weapon.thrust[0] == 0){
            damage[1] = 0; //το thrust είναι μηδέν
        } else if (weapon.thrust[0] == 1) {
            damage[1] = (random.nextInt(weapon.thrust[1]) + 1) + weapon.thrust[2]; //ρίχνει ένα ζάρι και προσθέτει την τρίτη τιμή
        } else {
            damage[1] = (random.nextInt(weapon.thrust[1]) + 1) + (random.nextInt(weapon.thrust[1]) + 1) + weapon.thrust[2]; //ρίχνει δύο ζάρια και προσθέτει την τρίτη τιμή
        }

        if (weapon.magical[0] == 0){
            damage[2] = 0; //το magical είναι μηδέν
        }else if(weapon.magical[0] == 1){
            damage[0] =   (random.nextInt(weapon.magical[0]) + 1) + weapon.magical[2]; //ρίχνει ένα ζάρι και προσθέτει την τρίτη τιμή
        }else {
            damage[0] =   (random.nextInt(weapon.magical[0]) + 1) + (random.nextInt(weapon.magical[0]) + 1) + weapon.magical[2]; //ρίχνει δυο ζάρια και προσθέτει την τρίτη τιμή
        }
        return damage;

    }
}
