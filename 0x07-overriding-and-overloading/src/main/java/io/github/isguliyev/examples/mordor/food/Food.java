package io.github.isguliyev.examples.mordor.food;

public abstract class Food {
    private final int happinessPoints;

    public Food(int happinessPoints) {
        this.happinessPoints = happinessPoints;
    }

    public int getHappinessPoints() {
        return this.happinessPoints;
    }
}