package edu.neumont.csc150.models.items.potions;

import edu.neumont.csc150.models.interfaces.Consumable;
import edu.neumont.csc150.models.items.Item;

public abstract class Potion extends Item implements Consumable {
    private int potency;
    private boolean consumed;

    public Potion(String name, String description, int value, int potency) {
        super(name, description, value);
        setPotency(potency);
        setConsumed(false);
    }

	@Override
    public void consume() {
        setConsumed(true);
    }

	@Override
    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public int getPotency() {
        return potency;
    }

    public void setPotency(int potency) {
        this.potency = potency;
    }

    @Override
    public String toString() {
        return super.toString() + " | Potency: " + potency;
    }
}
