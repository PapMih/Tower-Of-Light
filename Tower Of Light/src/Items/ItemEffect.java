package Items;

public class ItemEffect {
    private final EffectType effectType;
    final int amount;
    int remain;

    public ItemEffect(EffectType effectType, int amount, int remain) {
        this.effectType = effectType;
        this.amount = amount;
        this.remain = remain;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public int getAmount() {
        return amount;
    } // το αρχικό ποσό

    public int getRemain() {
        return remain;
    } //το ποσό που μένει

}
