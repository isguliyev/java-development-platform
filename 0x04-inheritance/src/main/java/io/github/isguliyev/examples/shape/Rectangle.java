package io.github.isguliyev.examples.shape;

public class Rectangle extends GeometricShape {
    private double height;
    private double width;

    public Rectangle() {}

    public Rectangle(double height, double width) {
        this.setHeight(height);
        this.setWidth(width);
    }

    @Override
    public double area() {
        return this.height * this.width;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [height=%.2f, width=%.2f]",
            this.getClass().getSimpleName(),
            this.height,
            this.width
        );
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }

        this.height = height;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }

        this.width = width;
    }
}