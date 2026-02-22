package edu.neumont.csc150.models.items.potions;

import edu.neumont.csc150.models.Player;
import edu.neumont.csc150.models.StatusEffect;
import edu.neumont.csc150.models.interfaces.StatusApplicable;


public class RegenPotion extends Potion implements StatusApplicable {
	private StatusEffect effect;

	public RegenPotion() {
		super("Regen Potion", "A glowing green elixir that heals over time", 15, 10);
		setEffect(StatusEffect.REGEN);
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
