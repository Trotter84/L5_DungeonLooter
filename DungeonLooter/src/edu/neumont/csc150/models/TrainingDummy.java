package edu.neumont.csc150.models;

public class TrainingDummy extends Player {

    public TrainingDummy() {
        super("Training Dummy", 9999, 8, 0);
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive()) {
            heal(getMaxHealth());
        }
    }
}
