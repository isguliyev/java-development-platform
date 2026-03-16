package io.github.isguliyev.examples.character;

public class GameCharacter {
    public static final int MAX_HEALTH = 100;
    public static final int MIN_HEALTH = 0;

    private final String name;
    private int currentHealth;
    private Status status;

    public GameCharacter(String name, int currentHealth) {
        if (name == null) {
            throw new NullPointerException("Failed to initialize game character: name is null");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Failed to initialize game character: name is empty");
        }

        this.name = name;
        setCurrentHealth(currentHealth);
    }

    @Override
    public String toString() {
        return String.format(
            "%s [name=%s, currentHealth=%d, status=%s]",
            this.getClass().getSimpleName(),
            this.name,
            this.currentHealth,
            this.status
        );
    }

    public void takeDamage(int damageAmount) {
        if (damageAmount < 0) {
            throw new IllegalArgumentException("Failed to take damage: damage amount is negative");
        }

        setCurrentHealth(this.currentHealth - damageAmount);
    }

    public void receiveHealing(int healingAmount) {
        if (healingAmount < 0) {
            throw new IllegalArgumentException("Failed to receive healing: healing amount is negative");
        }

        setCurrentHealth(this.currentHealth + healingAmount);
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public String getName() {
        return this.name;
    }

    public Status getStatus() {
        return this.status;
    }

    private void setCurrentHealth(int currentHealth) {
        if (currentHealth <= MIN_HEALTH) {
            this.currentHealth = MIN_HEALTH;
            this.status = Status.DEAD;
        } else if (currentHealth >= MAX_HEALTH) {
            this.currentHealth = MAX_HEALTH;
            this.status = Status.ALIVE;
        } else {
            this.currentHealth = currentHealth;
            this.status = Status.ALIVE;
        }
    }
}