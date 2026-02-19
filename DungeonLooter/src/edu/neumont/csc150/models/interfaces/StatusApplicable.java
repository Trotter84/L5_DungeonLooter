package edu.neumont.csc150.models.interfaces;

import edu.neumont.csc150.models.Player;
import edu.neumont.csc150.models.StatusEffect;

/**
 * Any item that is capable of applying a status effect (poison, burn, regen, strength, etc.) to a target.
 * Think of the name as "Status-Applicable" — meaning "I can apply a status," not "a status can be applied to me."
 */
public interface StatusApplicable {

	StatusEffect getEffect();

	void applyEffect(Player target);

	void removeEffect(Player target);


	default String getEffectDescription() {
		return getEffect().name() + " for " + getEffect().getDuration() + "turns";
	}
}
