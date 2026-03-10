package io.github.isguliyev.examples.mordor;

import io.github.isguliyev.examples.mordor.food.Food;
import io.github.isguliyev.examples.mordor.food.MockFood;

import io.github.isguliyev.examples.mordor.mood.Angry;
import io.github.isguliyev.examples.mordor.mood.Sad;
import io.github.isguliyev.examples.mordor.mood.Happy;
import io.github.isguliyev.examples.mordor.mood.VeryHappy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {
    public final int sadThreshold = Character.SAD_THRESHOLD;
    public final int happyThreshold = Character.HAPPY_THRESHOLD;
    public final int veryHappyThreshold = Character.VERY_HAPPY_THRESHOLD;

    private Character character;

    @BeforeEach
    void setUp() {
        this.character = new Character("Gandalf");
    }

    @Test
    void eat_addsHappinessPoints_whenFoodsAreValid() {
        final int expectedHappinessPoints = 10;

        this.character.eat(new Food[] {new MockFood(expectedHappinessPoints)});

        final int actualHappinessPoints = this.character.getHappinessPoints();

        assertEquals(expectedHappinessPoints, actualHappinessPoints);
    }

    @Nested
    class GetCurrentMoodTest {
        @Test
        void getCurrentMood_returnsAngry_whenHappinessPointsAreBelowSadThreshold() {
            character.eat(new Food[] {new MockFood(sadThreshold - 1)});

            assertEquals(Angry.INSTANCE, character.getCurrentMood());
        }

        @Test
        void getCurrentMood_returnsAngry_whenHappinessPointsEqualsSadThreshold() {
            character.eat(new Food[] {new MockFood(sadThreshold)});

            assertEquals(Angry.INSTANCE, character.getCurrentMood());
        }

        @Test
        void getCurrentMood_returnsSad_whenHappinessPointsAreBelowHappyThreshold() {
            character.eat(new Food[] {new MockFood(happyThreshold - 1)});

            assertEquals(Sad.INSTANCE, character.getCurrentMood());
        }

        @Test
        void getCurrentMood_returnsSad_whenHappinessPointsEqualsHappyThreshold() {
            character.eat(new Food[] {new MockFood(happyThreshold)});

            assertEquals(Sad.INSTANCE, character.getCurrentMood());
        }

        @Test
        void getCurrentMood_returnsHappy_whenHappinessPointsAreBelowVeryHappyThreshold() {
            character.eat(new Food[] {new MockFood(veryHappyThreshold - 1)});

            assertEquals(Happy.INSTANCE, character.getCurrentMood());
        }

        @Test
        void getCurrentMood_returnsHappy_whenHappinessPointsEqualsVeryHappyThreshold() {
            character.eat(new Food[] {new MockFood(veryHappyThreshold)});

            assertEquals(Happy.INSTANCE, character.getCurrentMood());
        }

        @Test
        void getCurrentMood_returnsVeryHappy_whenHappinessPointsAreAboveVeryHappyThreshold() {
            character.eat(new Food[] {new MockFood(veryHappyThreshold + 1)});

            assertEquals(VeryHappy.INSTANCE, character.getCurrentMood());
        }
    }
}