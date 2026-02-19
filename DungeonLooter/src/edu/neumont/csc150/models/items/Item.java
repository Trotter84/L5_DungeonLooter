package edu.neumont.csc150.models.items;

import java.util.function.ToDoubleBiFunction;
//TODO: note the Comparable implement
public abstract class Item implements Comparable {
    private String name;
    private String description;
    private int value;

    public Item(String name, String description, int value) {
        setName(name);
        setDescription(description);
        setValue(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

//	TODO: Check back later
    @Override
    public int compareTo(Object other) {
        Item otherItem = (Item) other;
        return Integer.compare(otherItem.getValue(), this.getValue());
    }

    @Override
    public String toString() {
        return name + " - " + description + " (Worth: " + value + " gold)";
    }
}
