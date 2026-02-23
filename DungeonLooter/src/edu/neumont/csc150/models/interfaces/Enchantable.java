package edu.neumont.csc150.models.interfaces;

/**
 * A special type of {@code StatusApplicable} item that also carries an enchantment name and bonus value.
 * Because it extends {@code StatusApplicable}, any class that implements {@code Enchantable} must also satisfy the {@code StatusApplicable} contract.
 */
public interface Enchantable extends StatusApplicable {

	String getEnchantment();

	int getEnchantmentBonus();
	
}
