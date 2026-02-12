package edu.neumont.csc150.models;

import edu.neumont.csc150.models.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public int getCount() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    public List<Item> getWeapons() {
        return getAllItems();
    }

    public List<Item> getEquipment() {
        return getAllItems();
    }

    public List<Item> getConsumables() {
        return getAllItems();
    }
}
