package Items;

import java.util.List;
import java.util.Random;

//χρήση enum για την αντιστοίχιση των όπλων ανά επίπεδο
public enum WeaponsLevel {  LEVEL1(List.of(new Weapons.Mace(), new Weapons.Dagger())),
    LEVEL2(List.of(new Weapons.Mace(), new Weapons.Dagger(), new Weapons.BladeOfLight(), new Weapons.SwordOfChaos(), new Weapons.Staff(), new Weapons.SummoningStaff())),
    LEVEL3(List.of(new Weapons.BladeOfLight(), new Weapons.SwordOfChaos(), new Weapons.Staff(), new Weapons.SummoningStaff(), new Weapons.HammerofWrath(), new Weapons.EbonBlade())),
    LEVEL4(List.of(new Weapons.BladeOfLight(), new Weapons.SwordOfChaos(), new Weapons.Staff(), new Weapons.SummoningStaff(), new Weapons.HammerofWrath(), new Weapons.EbonBlade(),
            new Weapons.DivineHammer())),
    LEVEL5(List.of(new Weapons.HammerofWrath(), new Weapons.EbonBlade(), new Weapons.DivineHammer(), new Weapons.DemonClaws())),
    LEVEL6(List.of(new Weapons.DivineHammer(), new Weapons.DemonClaws(), new Weapons.Lightbringer(), new Weapons.EdgeOfChaos()))
    ;

    final List<Weapons> weapons;


    WeaponsLevel(List<Weapons> weapons) {
        this.weapons = weapons;
    }

    //μέθοδος για την επιλογή τυχαίου όπλου ανά επίπεδο
    public static Weapons weaponRandomSelection(String level){
        WeaponsLevel weaponsLevel = WeaponsLevel.valueOf(level); //μετατροπή του string σε τιμή του enum
        int size = weaponsLevel.weapons.size(); //το μέγεθος του
        Random random = new Random();
        int randomInt = random.nextInt(size); //επιλέγω τυχαίο στοιχείο από τη λίστα
        return weaponsLevel.weapons.get(randomInt);
    }
}
