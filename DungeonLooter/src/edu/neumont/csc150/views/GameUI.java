package edu.neumont.csc150.views;

import edu.neumont.csc150.models.ActiveEffect;
import edu.neumont.csc150.models.*;
import edu.neumont.csc150.models.items.equipment.*;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.encryption.*;

import java.util.List;

public class GameUI {
    private static final Console.Color32 GOLD = new Console.Color32(0xFFD700);
    private static final Console.Color32 BLOOD_RED = new Console.Color32(0xCC0000);
    private static final Console.Color32 HEAL_GREEN = new Console.Color32(0x33CC33);
    private static final Console.Color32 ICE_BLUE = new Console.Color32(0x66CCFF);
    private static final Console.Color32 PURPLE_MAGIC = new Console.Color32(0xBB44FF);
    private static final Console.Color32 FIRE_ORANGE = new Console.Color32(0xFF6611);
    private static final Console.Color32 DIM_GRAY = new Console.Color32(0x666666);

    public static int displayMainMenu() {
        Console.writeln("");
        Console.writeRainbow("=============================");
        Console.writeln("");
        Console.writeln("      DUNGEON LOOTER", Console.TextColor.YELLOW, Console.TextStyle.BOLD);
        Console.writeRainbow("=============================");
        Console.writeln("");

        Console.writeln("1. Attack Training Dummy", Console.TextColor.RED);
        Console.writeln("2. Inventory", Console.TextColor.CYAN);
        Console.writeln("3. Equip", Console.TextColor.GREEN);
        Console.writeln("4. Player Stats", Console.TextColor.YELLOW);
        Console.writeln("5. Exit", DIM_GRAY);
        Console.writeRainbow("=============================");
        Console.writeln("");
        return Console.getIntInput("Choose an action: ", 1, 5, Console.TextColor.WHITE);
    }

    public static int displayInventoryMenu() {
        Console.writeln("");
        Console.writeln("--- INVENTORY ---", Console.TextColor.CYAN, Console.TextStyle.BOLD);
        Console.writeln("1. View All Items", Console.TextColor.WHITE);
        Console.writeln("2. Use Item", Console.TextColor.GREEN);
        Console.writeln("3. Inspect Item", Console.TextColor.YELLOW);
        Console.writeln("4. Back", DIM_GRAY);
        return Console.getIntInput("Choose an action: ", 1, 4, Console.TextColor.CYAN);
    }

    public static int displayEquipMenu() {
        Console.writeln("");
        Console.writeln("--- EQUIP ---", Console.TextColor.GREEN, Console.TextStyle.BOLD);
        Console.writeln("1. Equip Weapon", Console.TextColor.RED);
        Console.writeln("2. Equip Gear (Slot 1)", Console.TextColor.BLUE);
        Console.writeln("3. Equip Gear (Slot 2)", Console.TextColor.BLUE);
        Console.writeln("4. View Loadout", Console.TextColor.YELLOW);
        Console.writeln("5. Back", DIM_GRAY);
        return Console.getIntInput("Choose an action: ", 1, 5, Console.TextColor.GREEN);
    }

    public static void displayItems(List<Item> items) {
        if (items.isEmpty()) {
            Console.writeln("  (empty)", DIM_GRAY);
            return;
        }
        String[][] tableData = new String[items.size() + 1][3];
        tableData[0] = new String[]{"#", "Item", "Details"};
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            tableData[i + 1] = new String[]{
                    String.valueOf(i + 1),
                    item.getName(),
                    item.getDescription() + " (Worth: " + item.getValue() + "g)"
            };
        }
        Console.printTable(tableData, 3);
    }

    public static int selectItem(List<Item> items, String prompt) {
        if (items.isEmpty()) {
            Console.writeln("  No items available.", DIM_GRAY);
            return -1;
        }
        displayItems(items);
        Console.writeln("  " + (items.size() + 1) + ". Cancel", DIM_GRAY);
        int choice = Console.getIntInput(prompt, 1, items.size() + 1, Console.TextColor.WHITE);
        if (choice == items.size() + 1) {
            return -1;
        }
        return choice - 1;
    }

    public static void displayPlayerStats(Player player) {
        Console.writeln("");
        Console.writeln("--- " + player.getName().toUpperCase() + " ---", Console.TextColor.YELLOW, Console.TextStyle.BOLD);

        Console.write("  HP: ", Console.TextColor.WHITE);
        int healthPercent = (int) ((double) player.getHealth() / player.getMaxHealth() * 100);
        Console.Color32 healthColor = healthPercent > 50 ? HEAL_GREEN :
                healthPercent > 25 ? new Console.Color32(Console.TextColor.YELLOW) : BLOOD_RED;
        Console.writelnStatBar(player.getHealth(), player.getMaxHealth(), 20, healthColor, BLOOD_RED, true);

        Console.writeln("  ATK: " + player.getAttackPower(), Console.TextColor.RED);
        Console.writeln("  DEF: " + (player.getBaseDefense() + player.getEquipmentBonus("defense")), Console.TextColor.BLUE);

        List<ActiveEffect> effects = player.getActiveEffects();
        if (!effects.isEmpty()) {
            Console.writeln("  Active Effects:", Console.TextColor.PURPLE);
            for (ActiveEffect effect : effects) {
                Console.Color32 effectColor = effect.getEffect().getValuePerTurn() > 0 ? HEAL_GREEN : BLOOD_RED;
                Console.writeln("    - " + effect, effectColor);
            }
        }
    }

    public static void displayLoadout(Player player) {
        Console.writeln("");
        Console.writeln("--- LOADOUT ---", Console.TextColor.GREEN, Console.TextStyle.BOLD);
        Item weapon = player.getEquippedWeapon();
        Console.write("  Weapon: ", Console.TextColor.WHITE);
        if (weapon != null) {
            Console.writeln(weapon.toString(), Console.TextColor.RED);
        } else {
            Console.writeln("(none)", DIM_GRAY);
        }

        Equipment[] gear = player.getEquippedGear();
        for (int i = 0; i < gear.length; i++) {
            Console.write("  Gear Slot " + (i + 1) + ": ", Console.TextColor.WHITE);
            if (gear[i] != null) {
                Console.writeln(gear[i].toString(), Console.TextColor.BLUE);
            } else {
                Console.writeln("(empty)", DIM_GRAY);
            }
        }
    }

    public static void displayCombatResult(String playerName, String dummyName,
                                           int damageDealt, int damageTaken,
                                           String[] effectMessages) {
        Console.writeln("");
        Console.writeln("--- COMBAT ---", Console.TextColor.RED, Console.TextStyle.BOLD);

        Console.write("  " + playerName + " strikes the " + dummyName + " for ", Console.TextColor.WHITE);
        Console.write(String.valueOf(damageDealt), FIRE_ORANGE);
        Console.writeln(" damage!", Console.TextColor.WHITE);

        Console.write("  " + dummyName + " strikes back for ", Console.TextColor.WHITE);
        Console.write(String.valueOf(damageTaken), BLOOD_RED);
        Console.writeln(" damage!", Console.TextColor.WHITE);

        if (effectMessages != null && effectMessages.length > 0) {
            Console.writeln("  -- Status Effects --", Console.TextColor.PURPLE);
            for (String msg : effectMessages) {
                Console.writeln("  " + msg, PURPLE_MAGIC);
            }
        }
    }

    public static void displayLootDrop(Item item) {
        Console.write("  Loot dropped: ", Console.TextColor.WHITE);
        Console.writelnTyping(item.getName() + "!", 30, Console.TextColor.YELLOW);
    }

    public static void displayMessage(String message) {
        Console.writeln(message);
    }

    public static void displaySecretMessage(SecretMessage scroll) {
        Console.writeln("");
        Console.writeln("--- MYSTERIOUS SCROLL ---", Console.TextColor.PURPLE, Console.TextStyle.BOLD);
        Console.writeln("  The scroll reads:", Console.TextColor.WHITE);
        Console.writelnTyping("  \"" + scroll.getEncryptedText() + "\"", 15, Console.TextColor.PURPLE);
        Console.writeln("  (This text appears to be encrypted...)", DIM_GRAY);
    }

    public static void displayClue(Clue clue) {
        Console.writeln("");
        String[] lines = clue.readClue().split("\n");
        for (String line : lines) {
            if (line.startsWith("===")) {
                Console.writeRainbow(line);
                Console.writeln("");
            } else if (line.contains("CAESAR") || line.contains("CUTTER") || line.contains("VOWEL")) {
                Console.writelnTyping(line, 25, Console.TextColor.YELLOW);
            } else if (line.startsWith("- The Warden")) {
                Console.writeln(line, Console.TextColor.RED, Console.TextStyle.ITALIC);
            } else {
                Console.writelnTyping(line, 20, Console.TextColor.WHITE);
            }
        }
    }

    public static String getStringInput(String prompt) {
        Console.getInputOnSameLine = true;
        String input = Console.getStringInput(prompt, false, Console.TextColor.CYAN);
        Console.getInputOnSameLine = false;
        return input;
    }

    public static void showExitMessage() {
        Console.writeln("");
        Console.writelnTypingRainbow("Thanks for playing Dungeon Looter!", 40);
        Console.writeln("");
    }
}
