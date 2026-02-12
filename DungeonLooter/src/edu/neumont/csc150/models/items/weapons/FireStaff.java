package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.StatusEffect;

public class FireStaff extends Weapon {
    private StatusEffect effect;

    public FireStaff() {
        super("Fire Staff", "A staff crackling with flame", 35, 8);
        setEffect(StatusEffect.BURN);
    }

    public StatusEffect getEffect() {
        return effect;
    }

    public void setEffect(StatusEffect effect) {
        this.effect = effect;
    }
}
