package io.github.isguliyev.examples.character;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameCharacterTest {
    private final int maxHealth = GameCharacter.MAX_HEALTH;
    private final int minHealth = GameCharacter.MIN_HEALTH;

    private GameCharacter gameCharacterWithMaxHealth;
    private GameCharacter gameCharacterWithMinHealth;

    @BeforeEach
    void setUp() {
        this.gameCharacterWithMaxHealth = new GameCharacter("Alucard", maxHealth);
        this.gameCharacterWithMinHealth = new GameCharacter("Dracula", minHealth);
    }

    @Nested
    class ConstructorTest {
        @Test
        void constructor_throwsNullPointerException_whenNameIsNull() {
            assertThrows(NullPointerException.class, () -> new GameCharacter(null, maxHealth));
        }

        @Test
        void constructor_throwsIllegalArgumentException_whenNameIsEmpty() {
            assertThrows(IllegalArgumentException.class, () -> new GameCharacter("", maxHealth));
        }

        @Test
        void constructor_setsStatusAlive_whenCurrentHealthIsMoreThanMinHealth() {
            assertEquals(
                Status.ALIVE,
                new GameCharacter("Solid Snake", minHealth + 1).getStatus()
            );
        }

        @Test
        void constructor_setsStatusDead_whenCurrentHealthEqualsMinHealth() {
            assertEquals(Status.DEAD, new GameCharacter("Big Boss", minHealth).getStatus());
        }

        @Test
        void constructor_setsStatusDead_whenCurrentHealthIsLessThanMinHealth() {
            assertEquals(Status.DEAD, new GameCharacter("Venom Snake", minHealth - 1).getStatus());
        }

        @Test
        void constructor_setsCurrentHealthToMaxHealth_whenCurrentHealthIsMoreThanMaxHealth() {
            assertEquals(
                maxHealth,
                new GameCharacter("Master Miller", maxHealth + 1).getCurrentHealth()
            );
        }

        @Test
        void constructor_setsCurrentHealthToMinHealth_whenCurrentHealthIsLessThanMinHealth() {
            assertEquals(
                minHealth,
                new GameCharacter("Revolver Ocelot", minHealth - 1).getCurrentHealth()
            );
        }
    }

    @Nested
    class TakeDamageTest {
        @Test
        void takeDamage_subtractsDamageAmountFromCurrentHealth_whenDamageAmountIsPositive() {
            final int currentHealth = gameCharacterWithMaxHealth.getCurrentHealth();
            final int damageAmount = (minHealth + maxHealth) / 2;

            gameCharacterWithMaxHealth.takeDamage(damageAmount);

            assertEquals(
                currentHealth - damageAmount,
                gameCharacterWithMaxHealth.getCurrentHealth()
            );
        }

        @Test
        void takeDamage_throwsIllegalArgumentException_whenDamageAmountIsNegative() {
            assertThrows(
                IllegalArgumentException.class,
                () -> gameCharacterWithMaxHealth.takeDamage(-1)
            );
        }

        @Test
        void takeDamage_setsStatusDead_whenDamageAmountEqualsCurrentHealth() {
            final int currentHealth = gameCharacterWithMaxHealth.getCurrentHealth();

            gameCharacterWithMaxHealth.takeDamage(currentHealth);

            assertEquals(Status.DEAD, gameCharacterWithMaxHealth.getStatus());
        }

        @Test
        void takeDamage_setsStatusDead_whenDamageAmountIsMoreThanCurrentHealth() {
            final int currentHealth = gameCharacterWithMaxHealth.getCurrentHealth();

            gameCharacterWithMaxHealth.takeDamage(currentHealth + 1);

            assertEquals(Status.DEAD, gameCharacterWithMaxHealth.getStatus());
        }

        @Test
        void takeDamage_setsCurrentHealthToMinHealth_whenDamageAmountIsMoreThanCurrentHealth() {
            final int currentHealth = gameCharacterWithMaxHealth.getCurrentHealth();

            gameCharacterWithMaxHealth.takeDamage(currentHealth + 1);

            assertEquals(minHealth, gameCharacterWithMaxHealth.getCurrentHealth());
        }
    }

    @Nested
    class ReceiveHealingTest {
        @Test
        void receiveHealing_addsHealingAmountToCurrentHealth_whenHealingAmountIsPositive() {
            final int currentHealth = gameCharacterWithMinHealth.getCurrentHealth();
            final int healingAmount = (minHealth + maxHealth) / 2;

            gameCharacterWithMinHealth.receiveHealing(healingAmount);

            assertEquals(
                currentHealth + healingAmount,
                gameCharacterWithMinHealth.getCurrentHealth()
            );
        }

        @Test
        void receiveHealing_throwsIllegalArgumentException_whenHealingAmountIsNegative() {
            assertThrows(
                IllegalArgumentException.class,
                () -> gameCharacterWithMaxHealth.receiveHealing(-1)
            );
        }

        @Test
        void receiveHealing_setsStatusAlive_whenHealingAmountIsMoreThanMinHealth() {
            gameCharacterWithMinHealth.receiveHealing(minHealth + 1);

            assertEquals(Status.ALIVE, gameCharacterWithMinHealth.getStatus());
        }

        @Test
        void receiveHealing_setsCurrentHealthToMaxHealth_whenSumOfCurrentHealthAndHealingAmountIsMoreThanMaxHealth() {
            gameCharacterWithMinHealth.receiveHealing(maxHealth + 1);

            assertEquals(maxHealth, gameCharacterWithMinHealth.getCurrentHealth());
        }
    }
}