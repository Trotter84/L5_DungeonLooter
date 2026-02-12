package edu.neumont.csc150.models;

import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.weapons.*;
import edu.neumont.csc150.models.items.potions.*;
import edu.neumont.csc150.models.items.equipment.*;
import edu.neumont.csc150.models.encryption.SecretMessage;
import edu.neumont.csc150.models.encryption.Clue;

import java.util.Random;

public class LootTable {
    private Random random;
    private boolean scrollDropped;
    private boolean clueDropped;

    public LootTable() {
        this.random = new Random();
        this.scrollDropped = false;
        this.clueDropped = false;
    }

    public Item getRandomDrop() {
        int roll = random.nextInt(100);

        if (!scrollDropped && roll < 3) {
            scrollDropped = true;
            return new SecretMessage();
        } else if (!clueDropped && roll >= 3 && roll < 6) {
            clueDropped = true;
            return new Clue();
        } else if (roll < 18) {
            return new Sword();
        } else if (roll < 30) {
            return new Bow();
        } else if (roll < 40) {
            return new PoisonDagger();
        } else if (roll < 50) {
            return new FireStaff();
        } else if (roll < 62) {
            return new HealthPotion();
        } else if (roll < 72) {
            return new RegenPotion();
        } else if (roll < 80) {
            return new StrengthPotion();
        } else if (roll < 88) {
            return new Shield();
        } else if (roll < 95) {
            return new EnchantedAmulet();
        } else {
            return new DragonRing();
        }
    }
}
