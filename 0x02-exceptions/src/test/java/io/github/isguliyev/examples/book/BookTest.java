package io.github.isguliyev.examples.book;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookTest {
    @Test
    void constructor_throwsNullPointerException_whenTitleIsNull() {
        assertThrows(
            NullPointerException.class,
            () -> new Book(null, "Arthur C. Clarke", BigDecimal.valueOf(19.99d))
        );
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenTitleIsEmpty() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Book("", "John R. R. Tolkien", BigDecimal.valueOf(19.99d))
        );
    }

    @Test
    void constructor_throwsNullPointerException_whenAuthorIsNull() {
        assertThrows(
            NullPointerException.class,
            () -> new Book("Crafting Interpreters", null, BigDecimal.valueOf(19.99d))
        );
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenAuthorIsEmpty() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Book("The Art of Computer Programming", "", BigDecimal.valueOf(19.99d))
        );
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenPriceIsLessThan0() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Book("Odyssey", "Homer", BigDecimal.valueOf(-1))
        );
    }
}