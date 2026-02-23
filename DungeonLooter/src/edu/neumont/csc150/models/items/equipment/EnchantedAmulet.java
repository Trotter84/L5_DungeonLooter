package edu.neumont.csc150.models.items.equipment;

import edu.neumont.csc150.models.Player;
import edu.neumont.csc150.models.StatusEffect;
import edu.neumont.csc150.models.interfaces.Enchantable;


public class EnchantedAmulet extends Equipment implements Enchantable {
	private String enchantment;
	private int enchantmentBonus;
	private StatusEffect effect;

	public EnchantedAmulet() {
		super("Enchanted Amulet", "Glows with mystical energy", 50, 8, "health");
		setEnchantment("Vitality");
		setEnchantmentBonus(15);
		setEffect(StatusEffect.REGEN);
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
