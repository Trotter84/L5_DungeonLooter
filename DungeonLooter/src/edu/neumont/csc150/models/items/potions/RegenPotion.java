package edu.neumont.csc150.models.items.potions;

import edu.neumont.csc150.models.StatusEffect;

public class RegenPotion extends Potion {
    private StatusEffect effect;

    public RegenPotion() {
        super("Regen Potion", "A glowing green elixir that heals over time", 15, 10);
        setEffect(StatusEffect.REGEN);
    }

//	TODO: in RegenPotion, call getEffect()
    public StatusEffect getEffect() {
        return effect;
    }

    public void setEffect(StatusEffect effect) {
        this.effect = effect;
    }
}
