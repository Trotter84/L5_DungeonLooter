package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.*;
import edu.neumont.csc150.models.interfaces.Attackable;
import edu.neumont.csc150.models.interfaces.StatusApplicable;
import edu.neumont.csc150.models.items.*;
import edu.neumont.csc150.models.items.equipment.*;
import edu.neumont.csc150.models.items.weapons.*;
import edu.neumont.csc150.models.items.potions.*;
import edu.neumont.csc150.models.encryption.*;
import edu.neumont.csc150.views.*;

import java.util.Collections;
import java.util.List;


public class GameController {
	private Player player;
	private TrainingDummy dummy;
	private LootTable lootTable;

	public void run() {
		setup();
		do {
			int selection = GameUI.displayMainMenu();
			switch (selection) {
				case 1 -> attackDummy();
				case 2 -> inventory();
				case 3 -> equip();
				case 4 -> viewStats();
				default -> {
					exitGame();
					return;
				}
			}
		} while (true);
	}

	private void setup() {
		String name = GameUI.getStringInput("Enter your name, adventurer: ");
		player = new Player(name, 100, 5, 2);
		dummy = new TrainingDummy();
		lootTable = new LootTable();

		player.getInventory().addItem(new Sword());
		player.getInventory().addItem(new HealthPotion());

		GameUI.displayMessage("\nWelcome, " + name + "! You find yourself in a training arena.");
		GameUI.displayMessage("A sturdy Training Dummy stands before you. Time to practice!");
		GameUI.displayMessage("You start with an Iron Sword and a Health Potion in your bag.");
	}

	private void attackDummy() {
		if (player.getEquippedWeapon() == null) {
			GameUI.displayMessage("\nYou have nothing equipped to attack with! Visit the Equip menu first.");
			return;
		}

		int damageDealt = player.getAttackPower();
		dummy.takeDamage(damageDealt);

		if (player.getEquippedWeapon() instanceof StatusApplicable) {
			((StatusApplicable)player.getEquippedWeapon()).applyEffect(dummy);
		}

		int damageTaken = dummy.getAttackPower();
		player.takeDamage(damageTaken);

		String[] playerEffects = player.tickEffects();
		String[] dummyEffects = dummy.tickEffects();

		String[] allEffects = combineArrays(playerEffects, dummyEffects);

		GameUI.displayCombatResult(player.getName(), dummy.getName(), damageDealt, damageTaken, allEffects);

		if (!player.isAlive()) {
			GameUI.displayMessage("\n  You have been knocked out! The dummy stands victorious...");
			player.heal(player.getMaxHealth());
			GameUI.displayMessage("  You dust yourself off and get back up. (Health restored)");
		}

		Item loot = lootTable.getRandomDrop();
		player.getInventory().addItem(loot);
		GameUI.displayLootDrop(loot);
	}

	private void inventory() {
		do {
			int selection = GameUI.displayInventoryMenu();
			switch (selection) {
				case 1 -> viewItems();
				case 2 -> useItem();
				case 3 -> inspectItem();
				default -> {
					return;
				}
			}
		} while (true);
	}

	private void viewItems() {
		GameUI.displayMessage("\n--- YOUR ITEMS ---");
		List<Item> items = player.getInventory().getAllItems();
		GameUI.displayItems(items);
	}

	private void useItem() {
		GameUI.displayMessage("\n--- USE ITEM ---");
		List<Item> usable = player.getInventory().getConsumables();
		Collections.sort(usable);
		int choice = GameUI.selectItem(usable, "Choose an item to use: ");
		if (choice == -1)
			return;

		Item selected = usable.get(choice);
		if (selected instanceof Potion) {
			Potion potion = (Potion)selected;
			potion.consume();

			if (potion instanceof StatusApplicable) {
				((StatusApplicable)potion).applyEffect(player);
			}
			player.heal(potion.getPotency());
			GameUI.displayMessage("  You used " + potion.getName() + " and recovered " + potion.getPotency() + " health!");

			int inventoryIndex = findItemIndex(selected);
			if (inventoryIndex >= 0) {
				player.getInventory().removeItem(inventoryIndex);
			}
		} else {
			GameUI.displayMessage("  You can't figure out how to use that...");
		}
	}

	private void inspectItem() {
		GameUI.displayMessage("\n--- INSPECT ITEM ---");
		List<Item> allItems = player.getInventory().getAllItems();
		int choice = GameUI.selectItem(allItems, "Choose an item to inspect: ");
		if (choice == -1)
			return;

		Item selected = allItems.get(choice);
		GameUI.displayMessage("\n  === " + selected.getName() + " ===");
		GameUI.displayMessage("  " + selected.getDescription());
		GameUI.displayMessage("  Value: " + selected.getValue() + " gold");

		if (selected instanceof Weapon) {
			GameUI.displayMessage("  Damage: " + ((Weapon)selected).getDamage());
		} else if (selected instanceof Potion) {
			GameUI.displayMessage("  Potency: " + ((Potion)selected).getPotency());
		} else if (selected instanceof Equipment) {
			Equipment eq = (Equipment)selected;
			GameUI.displayMessage("  Bonus: +" + eq.getStatBonus() + " " + eq.getStatType());
		}

		if (selected instanceof SecretMessage) {
			GameUI.displaySecretMessage((SecretMessage)selected);
		} else if (selected instanceof Clue) {
			GameUI.displayClue((Clue)selected);
		}
	}

	private void equip() {
		do {
			int selection = GameUI.displayEquipMenu();
			switch (selection) {
				case 1 -> equipWeapon();
				case 2 -> equipGear(0);
				case 3 -> equipGear(1);
				case 4 -> GameUI.displayLoadout(player);
				default -> {
					return;
				}
			}
		} while (true);
	}

	private void equipWeapon() {
		GameUI.displayMessage("\n--- EQUIP WEAPON ---");
		List<Item> weapons = player.getInventory().getWeapons();
		Collections.sort(weapons);
		int choice = GameUI.selectItem(weapons, "Choose a weapon to equip: ");
		if (choice == -1)
			return;

		Item selected = weapons.get(choice);
		int inventoryIndex = findItemIndex(selected);

		player.equipWeapon((Attackable)selected);
		if (inventoryIndex >= 0) {
			player.getInventory().removeItem(inventoryIndex);
		}
		GameUI.displayMessage("  Equipped: " + selected.getName());
	}

	private void equipGear(int slot) {
		GameUI.displayMessage("\n--- EQUIP GEAR (Slot " + (slot + 1) + ") ---");
		List<Item> gear = player.getInventory().getEquipment();
		Collections.sort(gear);
		int choice = GameUI.selectItem(gear, "Choose gear to equip: ");
		if (choice == -1)
			return;

		Item selected = gear.get(choice);
		if (selected instanceof Equipment) {
			Item oldGear = player.getEquippedGear()[slot];
			if (oldGear instanceof StatusApplicable) {
				((StatusApplicable)oldGear).removeEffect(player);
			}
			int inventoryIndex = findItemIndex(selected);
			player.equipGear((Equipment)selected, slot);
			if (selected instanceof StatusApplicable) {
				((StatusApplicable)selected).applyEffect(player);
			}
			if (inventoryIndex >= 0) {
				player.getInventory().removeItem(inventoryIndex);
			}
			GameUI.displayMessage("  Equipped in slot " + (slot + 1) + ": " + selected.getName());
		} else {
			GameUI.displayMessage("  That doesn't seem like something you can wear...");
		}
	}

	private void viewStats() {
		GameUI.displayPlayerStats(player);
		GameUI.displayPlayerStats(dummy);
	}

	private void exitGame() {
		GameUI.showExitMessage();
	}

	private int findItemIndex(Item target) {
		List<Item> all = player.getInventory().getAllItems();
		for (int i = 0; i < all.size(); i++) {
			if (all.get(i) == target) {
				return i;
			}
		}
		return -1;
	}

	private String[] combineArrays(String[] a, String[] b) {
		String[] combined = new String[a.length + b.length];
		int index = 0;
		for (String s : a) {
			combined[index++] = s;
		}
		for (String s : b) {
			combined[index++] = s;
		}
		return combined;
	}
}
