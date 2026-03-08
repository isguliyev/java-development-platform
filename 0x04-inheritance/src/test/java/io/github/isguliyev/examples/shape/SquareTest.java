package io.github.isguliyev.examples.shape;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SquareTest {
    private Square square;

    @BeforeEach
    void setUp() {
        this.square = new Square();
    }

    @Test
    void constructor_setsHeightAndWidth_whenSideIsPositive() {
        final double side = 1.0d;

        Square square = new Square(side);

        assertAll(
            () -> assertEquals(side, square.getHeight()),
            () -> assertEquals(side, square.getWidth())
        );
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenSideIsNotPositive() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Square(0)),
            () -> assertThrows(IllegalArgumentException.class, () -> new Square(-1))
        );
    }

    @Test
    void setSide_setsHeightAndWidth_whenSideIsPositive() {
        final double side = 1.0d;

        this.square.setSide(side);

        assertAll(
            () -> assertEquals(side, this.square.getHeight()),
            () -> assertEquals(side, this.square.getWidth())
        );
    }

    @Test
    void setSide_throwsIllegalArgumentException_whenSideIsNotPositive() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> this.square.setSide(0)),
            () -> assertThrows(IllegalArgumentException.class, () -> this.square.setSide(-1))
        );
    }
}