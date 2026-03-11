package io.github.isguliyev.examples.shape;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {
    private Rectangle rectangle;

    @BeforeEach
    void setUp() {
        this.rectangle = new Rectangle();
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenHeightIsNotPositive() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Rectangle(0.0d, 1.0d)),
            () -> assertThrows(IllegalArgumentException.class, () -> new Rectangle(-1.0d, 1.0d))
        );
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenWidthIsNotPositive() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Rectangle(1.0d, 0.0d)),
            () -> assertThrows(IllegalArgumentException.class, () -> new Rectangle(1.0d, -1.0d))
        );
    }

    @Test
    void setWidth_throwsIllegalArgumentException_whenWidthIsNotPositive() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> this.rectangle.setWidth(0.0d)),
            () -> assertThrows(IllegalArgumentException.class, () -> this.rectangle.setWidth(-1.0d))
        );
    }

    @Test
    void setHeight_throwsIllegalArgumentException_whenHeightIsNotPositive() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> this.rectangle.setHeight(0.0d)),
            () -> assertThrows(IllegalArgumentException.class, () -> this.rectangle.setHeight(-1.0d))
        );
    }

    @Test
    void area_returnsAreaOfRectangle() {
        final double height = 1.0d;
        final double width = 2.0d;

        this.rectangle.setHeight(height);
        this.rectangle.setWidth(width);

        assertEquals(height * width, this.rectangle.area());
    }
}