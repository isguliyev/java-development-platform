package io.github.isguliyev.examples.shape;

public class Square extends Rectangle {
    private double side;

    public Square() {}

    public Square(double side) {
        this.setSide(side);
    }

    public double getSide() {
        return this.side;
    }

    public void setSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be positive");
        }

        this.side = side;
        super.setWidth(side);
        super.setHeight(side);
    }

    @Override
    public void setHeight(double height) {
        this.setSide(height);
    }

    @Override
    public void setWidth(double width) {
        this.setSide(width);
    }
}