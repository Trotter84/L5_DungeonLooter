package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.Player;
import edu.neumont.csc150.models.StatusEffect;
import edu.neumont.csc150.models.interfaces.StatusApplicable;

public class FireStaff extends Weapon implements StatusApplicable {
    private StatusEffect effect;

    public FireStaff() {
        super("Fire Staff", "A staff crackling with flame", 35, 8);
        setEffect(StatusEffect.BURN);
    }

//	TODO: in FireStaff, call getEffect()
    public StatusEffect getEffect() {
        return effect;
    }

	@Override
	public void applyEffect(Player target) {
		target.addEffect(getEffect());
	}

	@Override
	public void removeEffect(Player target) {
		target.removeEffect(getEffect());
	}

	public void setEffect(StatusEffect effect) {
        this.effect = effect;
    }
}
