package io.github.isguliyev.examples.integer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.ValueSource;

public class PositiveIntegerTest {
    private PositiveInteger positiveInteger;

    @BeforeEach
    void setUp() {
        this.positiveInteger = new PositiveInteger();
    }

    @Test
    void constructors_throwsIllegalArgumentException_whenValueIsNegative() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new PositiveInteger(-1)),
            () -> assertThrows(IllegalArgumentException.class, () -> new PositiveInteger("-1"))
        );
    }

    @Test
    void setValue_throwsIllegalArgumentException_whenValueIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> this.positiveInteger.setValue(-1));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47})
    void isPrime_returnsTrue_whenValueIsPrime(int value) {
        this.positiveInteger.setValue(value);

        assertTrue(this.positiveInteger.isPrime());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25})
    void isPrime_returnsFalse_whenValueIsNotPrime(int value) {
        this.positiveInteger.setValue(value);

        assertFalse(this.positiveInteger.isPrime());
    }

    @Test
    void isPrime_returnsFalse_whenValueIsOne() {
        this.positiveInteger.setValue(1);

        assertFalse(this.positiveInteger.isPrime());
    }
}