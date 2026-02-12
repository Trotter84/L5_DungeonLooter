package edu.neumont.csc150.models.items.potions;

import edu.neumont.csc150.models.StatusEffect;

public class StrengthPotion extends Potion {
    private StatusEffect effect;

    public StrengthPotion() {
        super("Strength Potion", "A fiery orange brew that empowers the drinker", 20, 0);
        setEffect(StatusEffect.STRENGTH);
    }

    public StatusEffect getEffect() {
        return effect;
    }

    public void setEffect(StatusEffect effect) {
        this.effect = effect;
    }
}
