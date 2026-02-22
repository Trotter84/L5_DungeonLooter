package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.Player;
import edu.neumont.csc150.models.StatusEffect;
import edu.neumont.csc150.models.interfaces.StatusApplicable;


public class PoisonDagger extends Weapon implements StatusApplicable {
	private StatusEffect effect;

	public PoisonDagger() {
		super("Poison Dagger", "A dagger dripping with venom", 20, 10);
		setEffect(StatusEffect.POISON);
	}

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
