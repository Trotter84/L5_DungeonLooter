package edu.neumont.csc150.models;

public enum StatusEffect {
    POISON(3, -5, "Poison seeps into the veins..."),
    BURN(2, -8, "Flames lick at the flesh..."),
    REGEN(4, 5, "A warm glow mends the wounds..."),
    STRENGTH(3, 10, "Raw power surges through the body!"),
    WEAKNESS(3, -10, "A feeble feeling takes hold...");

    private final int duration;
    private final int valuePerTurn;
    private final String tickMessage;

    StatusEffect(int duration, int valuePerTurn, String tickMessage) {
        this.duration = duration;
        this.valuePerTurn = valuePerTurn;
        this.tickMessage = tickMessage;
    }

    public int getDuration() {
        return duration;
    }

    public int getValuePerTurn() {
        return valuePerTurn;
    }

    public String getTickMessage() {
        return tickMessage;
    }
}
