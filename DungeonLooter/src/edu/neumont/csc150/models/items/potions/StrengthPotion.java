package edu.neumont.csc150.models.items.potions;

import edu.neumont.csc150.models.Player;
import edu.neumont.csc150.models.StatusEffect;
import edu.neumont.csc150.models.interfaces.StatusApplicable;

public class StrengthPotion extends Potion implements StatusApplicable {
    private StatusEffect effect;

    public StrengthPotion() {
        super("Strength Potion", "A fiery orange brew that empowers the drinker", 20, 0);
        setEffect(StatusEffect.STRENGTH);
    }

//	TODO: in StrengthPotion, call getEffect()
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
