package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.StatusEffect;

public class PoisonDagger extends Weapon {
    private StatusEffect effect;

    public PoisonDagger() {
        super("Poison Dagger", "A dagger dripping with venom", 20, 10);
        setEffect(StatusEffect.POISON);
    }

    public StatusEffect getEffect() {
        return effect;
    }

    public void setEffect(StatusEffect effect) {
        this.effect = effect;
    }
}
