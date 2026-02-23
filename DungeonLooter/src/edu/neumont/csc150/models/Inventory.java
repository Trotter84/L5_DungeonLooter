package edu.neumont.csc150.models;

import edu.neumont.csc150.models.interfaces.Attackable;
import edu.neumont.csc150.models.interfaces.Consumable;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.equipment.Equipment;

import java.util.ArrayList;
import java.util.List;


public class Inventory {
	private List<Item> items;
	private List<Item> consumables;
	private List<Item> weapons;
	private List<Item> equipment;

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
		weapons = new ArrayList<>();

		for (Item item : getAllItems()) {
			if (item instanceof Attackable) {
				weapons.add(item);
			}
		}
		return weapons;
	}

	public List<Item> getEquipment() {
		equipment = new ArrayList<>();

		for (Item item : getAllItems()) {
			if (item instanceof Equipment) {
				equipment.add(item);
			}
		}
		return equipment;
	}

	public List<Item> getConsumables() {
		consumables = new ArrayList<>();

		for (Item item : getAllItems()) {
			if (item instanceof Consumable) {
				consumables.add(item);
			}
		}
		return consumables;
	}

}
