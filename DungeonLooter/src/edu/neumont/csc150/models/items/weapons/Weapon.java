package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.interfaces.Attackable;
import edu.neumont.csc150.models.items.Item;

public abstract class Weapon extends Item implements Attackable {
    private int damage;

    public Weapon(String name, String description, int value, int damage) {
        super(name, description, value);
        setDamage(damage);
    }

    public int attack() {
        return getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return super.toString() + " | Damage: " + damage;
    }
}
