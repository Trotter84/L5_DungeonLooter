package edu.neumont.csc150.models;

import edu.neumont.csc150.models.interfaces.Attackable;
import edu.neumont.csc150.models.items.equipment.Equipment;
import edu.neumont.csc150.models.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int baseAttack;
    private int baseDefense;

//	TODO: equippedWeapon type
    private Item equippedWeapon;
    private Equipment[] equippedGear;
    private Inventory inventory;
    private List<ActiveEffect> activeEffects;

    public Player(String name, int maxHealth, int baseAttack, int baseDefense) {
        setName(name);
        setMaxHealth(maxHealth);
        setHealth(maxHealth);
        setBaseAttack(baseAttack);
        setBaseDefense(baseDefense);
        setEquippedWeapon(null);
        setEquippedGear(new Equipment[2]);
        setInventory(new Inventory());
        setActiveEffects(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Item equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Equipment[] getEquippedGear() {
        return equippedGear;
    }

    public void setEquippedGear(Equipment[] equippedGear) {
        this.equippedGear = equippedGear;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<ActiveEffect> getActiveEffects() {
        return activeEffects;
    }

    public void setActiveEffects(List<ActiveEffect> activeEffects) {
        this.activeEffects = activeEffects;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
    }

    public void takeDamage(int damage) {
        int totalDefense = baseDefense + getEquipmentBonus("defense");
        int actualDamage = Math.max(damage - totalDefense, 1);
        health -= actualDamage;
    }

    public int getAttackPower() {
        int total = baseAttack;
        if (equippedWeapon != null) {
            total += ((Attackable) equippedWeapon).attack();
        }
        total += getEquipmentBonus("attack");
        return total;
    }

    public int getEquipmentBonus(String statType) {
        int bonus = 0;
        for (int i = 0; i < equippedGear.length; i++) {
            if (equippedGear[i] != null && equippedGear[i].getStatType().equals(statType)) {
                bonus += equippedGear[i].getStatBonus();
            }
        }
        return bonus;
    }

    public void equipWeapon(Attackable weapon) {
        if (equippedWeapon != null) {
            inventory.addItem(equippedWeapon);
        }
        this.equippedWeapon = (Item) weapon;
    }

    public void equipGear(Equipment gear, int slot) {
        if (slot >= 0 && slot < equippedGear.length) {
            if (equippedGear[slot] != null) {
                inventory.addItem(equippedGear[slot]);
            }
            equippedGear[slot] = gear;
        }
    }

    public void addEffect(StatusEffect effect) {
        activeEffects.add(new ActiveEffect(effect));
    }

    public String[] tickEffects() {
        List<String> messages = new ArrayList<>();
        for (ActiveEffect active : activeEffects) {
            int value = active.tick();
            if (value > 0) {
                heal(value);
            } else {
                health += value;
            }
            messages.add(name + ": " + active.getEffect().getTickMessage() + " (" + value + " hp)");
        }
        activeEffects.removeIf(ActiveEffect::isExpired);
        return messages.toArray(new String[0]);
    }

    public void removeEffect(StatusEffect effectType) {
        activeEffects.removeIf(active -> active.getEffect() == effectType);
    }

    @Override
    public String toString() {
        return name + " | HP: " + health + "/" + maxHealth +
                " | ATK: " + getAttackPower() +
                " | DEF: " + (baseDefense + getEquipmentBonus("defense"));
    }
}
