package io.github.isguliyev.examples.pirate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PirateTest {
    @Test
    void constructor_throwsIllegalArgumentException_whenNameIsNull() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Pirate(null, "Captain", "Straw Hat Pirates")
        );
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenNameIsBlank() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Pirate(" ", "Captain", "Straw Hat Pirates")
        );
    }

    @Test
    void setId_throwsIllegalArgumentException_whenIdIsNull() {
        final Pirate pirate = new Pirate("Monkey D. Luffy", "Captain", "Straw Hat Pirates");

        assertThrows(IllegalArgumentException.class, () -> pirate.setId(null));
    }

    @Test
    void setId_throwsIllegalArgumentException_whenIdIsNotPositive() {
        final Pirate pirate = new Pirate("Monkey D. Luffy", "Captain", "Straw Hat Pirates");

        assertThrows(IllegalArgumentException.class, () -> pirate.setId(-1L));
    }

    @Test
    void setId_throwsIllegalStateException_whenIdIsAlreadySet() {
        final Pirate pirate = new Pirate(1L, "Monkey D. Luffy", "Captain", "Straw Hat Pirates");

        assertThrows(IllegalStateException.class, () -> pirate.setId(1L));
    }
}