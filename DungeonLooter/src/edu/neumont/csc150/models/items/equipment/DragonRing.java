package edu.neumont.csc150.models.items.equipment;

import edu.neumont.csc150.models.Player;
import edu.neumont.csc150.models.StatusEffect;
import edu.neumont.csc150.models.interfaces.Enchantable;


public class DragonRing extends Equipment implements Enchantable {
	private String enchantment;
	private int enchantmentBonus;
	private StatusEffect effect;

	public DragonRing() {
		super("Dragon Ring", "Forged in dragonfire", 45, 7, "attack");
		setEnchantment("Dragonfire");
		setEnchantmentBonus(12);
		setEffect(StatusEffect.STRENGTH);
	}

	public String getEnchantment() {
		return enchantment;
	}

	public void setEnchantment(String enchantment) {
		this.enchantment = enchantment;
	}

	public int getEnchantmentBonus() {
		return enchantmentBonus;
	}

	public void setEnchantmentBonus(int enchantmentBonus) {
		this.enchantmentBonus = enchantmentBonus;
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
