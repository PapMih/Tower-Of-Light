package Items;

import java.util.List;

import static Tiles.Tiles.TILE_SIZE;

public class Item {
    String name;
    String description;
    List<EffectType> effects;
    List<SlotType> slots;
    int itemX, itemY;

    Item(String name, String description, List<EffectType> effects, List<SlotType> slots) {

        this.slots = slots;
        this.name = name;
        this.description = description;
        this.effects = effects;
    }

    String getName(){
            return this.name;
    } //επιστρέφει το όνομα του αντικειμένου

    String getDescription(){
        return this.description;
    } //επιστρέφει την περιγραφή του αντικειμένου

    List<EffectType> getEffectType(){
        return this.effects;
    } //επιστρέφει τον EffectType

    List<SlotType> getSlotType(){
        return this.slots;
    }
}
