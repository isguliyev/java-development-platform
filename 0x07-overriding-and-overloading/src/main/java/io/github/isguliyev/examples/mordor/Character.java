package io.github.isguliyev.examples.mordor;

import io.github.isguliyev.examples.mordor.mood.Angry;
import io.github.isguliyev.examples.mordor.mood.Happy;
import io.github.isguliyev.examples.mordor.mood.Mood;
import io.github.isguliyev.examples.mordor.mood.Sad;
import io.github.isguliyev.examples.mordor.mood.VeryHappy;

import io.github.isguliyev.examples.mordor.food.Food;

public class Character {
    public static final int SAD_THRESHOLD = -5;
    public static final int HAPPY_THRESHOLD = 0;
    public static final int VERY_HAPPY_THRESHOLD = 15;

    private final String name;
    private int happinessPoints;

    public Character(String name) {
        this.name = name;
        this.happinessPoints = 0;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [name=%s, happinessPoints=%d, currentMood=%s]",
            this.getClass().getSimpleName(),
            this.name,
            this.happinessPoints,
            this.getCurrentMood().getClass().getSimpleName()
        );
    }

    public void eat(Food[] foods) {
        for (Food food : foods) {
            this.happinessPoints += food.getHappinessPoints();
        }
    }

    public Mood getCurrentMood() {
        if (this.happinessPoints > VERY_HAPPY_THRESHOLD) {
            return VeryHappy.INSTANCE;
        }

        if (this.happinessPoints > HAPPY_THRESHOLD) {
            return Happy.INSTANCE;
        }

        if (this.happinessPoints > SAD_THRESHOLD) {
            return Sad.INSTANCE;
        }

        return Angry.INSTANCE;
    }

    public int getHappinessPoints() {
        return this.happinessPoints;
    }

    public String getName() {
        return this.name;
    }
}