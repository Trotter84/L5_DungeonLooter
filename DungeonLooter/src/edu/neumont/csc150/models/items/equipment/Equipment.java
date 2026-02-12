package edu.neumont.csc150.models.items.equipment;

import edu.neumont.csc150.models.items.Item;

public abstract class Equipment extends Item implements Comparable {
    private int statBonus;
    private String statType;

    public Equipment(String name, String description, int value, int statBonus, String statType) {
        super(name, description, value);
        setStatBonus(statBonus);
        setStatType(statType);
    }

    public int getStatBonus() {
        return statBonus;
    }

    public void setStatBonus(int statBonus) {
        this.statBonus = statBonus;
    }

    public String getStatType() {
        return statType;
    }

    public void setStatType(String statType) {
        this.statType = statType;
    }

    @Override
    public int compareTo(Object other) {
        Equipment otherEquipment = (Equipment) other;
        return Integer.compare(otherEquipment.getStatBonus(), this.getStatBonus());
    }

    @Override
    public String toString() {
        return super.toString() + " | +" + statBonus + " " + statType;
    }
}
