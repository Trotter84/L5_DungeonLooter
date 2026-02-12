package edu.neumont.csc150.models;

public class ActiveEffect {
    private StatusEffect effect;
    private int remainingTurns;

    public ActiveEffect(StatusEffect effect) {
        setEffect(effect);
        setRemainingTurns(effect.getDuration());
    }

    public StatusEffect getEffect() {
        return effect;
    }

    public void setEffect(StatusEffect effect) {
        this.effect = effect;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public void setRemainingTurns(int remainingTurns) {
        this.remainingTurns = remainingTurns;
    }

    public boolean isExpired() {
        return remainingTurns <= 0;
    }

    public int tick() {
        remainingTurns--;
        return effect.getValuePerTurn();
    }

    @Override
    public String toString() {
        return effect.name() + " (" + remainingTurns + " turns left)";
    }
}
