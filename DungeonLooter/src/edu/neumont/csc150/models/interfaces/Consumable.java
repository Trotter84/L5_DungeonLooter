package edu.neumont.csc150.models.interfaces;

/**
 * Any item that can be used up (consumed) by the player.
 */
public interface Consumable {

	void consume();

	boolean isConsumed();

}