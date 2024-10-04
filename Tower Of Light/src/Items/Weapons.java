package Items;

import java.util.Arrays;
import java.util.List;

//έφτιαξα μια γενικη κλαση Weapons και inner class τα εκαστοτε οπλα ώστε να είναι πιο μαζεμένα
public class Weapons extends Equippable {

    Weapons(String name, String description, List<EffectType> effects, List<SlotType> slots, int[] swing, int[] thrust, int[] magical) {
        super(name, description, effects, slots, swing, thrust, magical);
    }

    public static class Mace extends Weapons { //static κλάσεις ώστε να μπορώ να δημιουργήσω αντικείμενο της εσωτερικής χωρίς να χρειάζεται να δημιουργήσω στην εξωτερικη κλαση
        public Mace() {
            super("Mace", "Swing: 1d6+1", Arrays.asList(EffectType.NONE), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{1, 6, 1}, new int[]{0, 0, 0}, new int[]{0, 0, 0});
        }
    }

    public static class Dagger extends Weapons {
        Dagger() {
            super("Dagger", "Thrust: 1d6+1", Arrays.asList(EffectType.NONE), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{0, 0, 0}, new int[]{1, 6, 1}, new int[]{0, 0, 0});
        }
    }

    public static class BladeOfLight extends Weapons {
        public BladeOfLight() {
            super("Blade of Light", "Thrust: 2d6+1 /n Magical: 1d3", Arrays.asList(EffectType.NONE), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{0, 0, 0}, new int[]{2, 6, 1}, new int[]{1, 3, 0});
        }
    }

    public static class SwordOfChaos extends Weapons {
        SwordOfChaos() {
            super("Sword of Chaos", "Thrust: 2d6+1 /n Magical: 1d3", Arrays.asList(EffectType.NONE), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{0, 0, 0}, new int[]{2, 6, 1}, new int[]{1, 3, 0});
        }
    }

    public static class Staff extends Weapons {
        public Staff() {
            super("Staff", "Swing: 2d6+2 /n Magical: 1d6+1", Arrays.asList(EffectType.NONE), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{2, 6, 2}, new int[]{0, 0, 0}, new int[]{1, 6, 1});
        }
    }

    public static class SummoningStaff extends Weapons {
        SummoningStaff() {
            super("Summoning Staff", "Swing: 2d6+2 /n Magical: 1d6+1", Arrays.asList(EffectType.NONE), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{2, 6, 2}, new int[]{0, 0, 0}, new int[]{1, 6, 1});
        }
    }

    public static class HammerofWrath extends Weapons {
        public HammerofWrath() {
            super("Hammer of Wrath", "Swing: 2d6+1 /n Magical: 2d6+1", Arrays.asList(EffectType.NONE), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{2, 6, 1}, new int[]{0, 0, 0}, new int[]{2, 6, 1});
        }
    }

    public static class EbonBlade extends Weapons {
        EbonBlade() {
            super("Ebon Blade", "Swing: 2d6+2 /n Magical:2 d6+2", Arrays.asList(EffectType.NONE), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{2, 6, 2}, new int[]{0, 0, 0}, new int[]{2, 6, 2});
        }
    }

    public static class DivineHammer extends Weapons {
        public DivineHammer() {
            super("Divine Hammer", "Thrust: 2d6+4 /n Magical: 2d6+2", Arrays.asList(EffectType.NONE), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{0, 0, 0}, new int[]{2, 6, 4}, new int[]{2, 6, 2});
        }
    }

    public static class DemonClaws extends Weapons {
        DemonClaws() {
            super("Demon Claws", "Swing: 2d6+4 /n Magical: 2d6+2", Arrays.asList(EffectType.NONE), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{2, 6, 4}, new int[]{0, 0, 0}, new int[]{2, 6, 2});
        }
    }

    public static class Lightbringer extends Weapons {
        public Lightbringer() {
            super("Lightbringer", "Swing: 1d6+2 /n Thrust: 3d6+2 /n Magical: 1d6+2", Arrays.asList(EffectType.INT_BONUS, EffectType.STR_BONUS, EffectType.MP_REPLENISH), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{1, 6, 2}, new int[]{3, 6, 2}, new int[]{1, 6, 2});
            ItemEffect intBonus = new ItemEffect(EffectType.INT_BONUS, 10,0);
            ItemEffect strBonus = new ItemEffect(EffectType.STR_BONUS, 10,0);
            ItemEffect mpReplenish = new ItemEffect(EffectType.MP_REPLENISH, 10,0);
        }
    }

    public static class  EdgeOfChaos extends Weapons {
        EdgeOfChaos() {
            super(" Edge of Chaos", "Swing: 1d6+2 /n Thrust: 3d6+2 /n Magical: 1d6+2", Arrays.asList(EffectType.INT_BONUS, EffectType.STR_BONUS, EffectType.MP_REPLENISH), Arrays.asList(SlotType.MAIN_HAND, SlotType.OFF_HAND), new int[]{1, 6, 2}, new int[]{3, 6, 2}, new int[]{1, 6, 2});
            ItemEffect intBonus = new ItemEffect(EffectType.INT_BONUS, 10,0);
            ItemEffect strBonus = new ItemEffect(EffectType.STR_BONUS, 10,0);
            ItemEffect mpReplenish = new ItemEffect(EffectType.MP_REPLENISH, 10,0);
        }
    }
}




