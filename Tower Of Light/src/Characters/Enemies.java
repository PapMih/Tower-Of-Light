package Characters;

import Items.Weapons;

public abstract class Enemies extends Characters {

    String name;
    int hp;
    Weapons weapon;
    int xpPoints;
    int[] res = new int[3]; // θεση 0 για swing, θέση 1 για thrust, θέση 2 για magical

    Enemies(String name, int hp, Weapons weapon, int xpPoints, int[] res){
        this.name = name;
        this.hp = hp;
        this.weapon = weapon;
        this.xpPoints = xpPoints;
        this.res = res;
    }

    int[] getResistance(){
        return res;
     }

    public int getXpPoints(){return this.xpPoints;}

   static class Priest extends Enemies{
       Priest(){
           super("Priest", 20, new Weapons.Mace(),30, new int[] {0, 0, 10000} ); //πολυ μεγαλη τιμη επειδή έχει ανοσία σε επιθέσεις magical
       }
   }

    static class Knight extends Enemies{
        Knight(){
            super("Knight", 30, new Weapons.BladeOfLight(), 50,  new int[] {3, 3, 1} );
        }
    }

    static class Bishop extends Enemies{
        Bishop(){
            super("Bishop", 40, new Weapons.Staff(), 60,  new int[] {0, 0, 5} );
        }
    }

    static class Paladin extends Enemies{
        Paladin(){
            super("Paladin", 100, new Weapons.HammerofWrath(), 80,  new int[] {3, 5, 2} );
        }
    }

    static class Archangel extends Enemies{
        Archangel(){
            super("Archangel", 130, new Weapons.DivineHammer(), 120,  new int[] {6, 6, 2} );
        }
    }

    static class HeraldOfLight extends Enemies{
        HeraldOfLight(){
            super("Herald Of Light", 160, new Weapons.Lightbringer(), 400,  new int[] {0, 0, 0} );// θα κάνω διαφορετική διαχείριση για αυτο βάζω μηδεν τις τιμές
        }
    }
}


