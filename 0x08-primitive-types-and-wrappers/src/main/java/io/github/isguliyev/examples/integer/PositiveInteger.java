package io.github.isguliyev.examples.integer;

public class PositiveInteger {
    private int value;

    public PositiveInteger() {
        this.value = 1;
    }

    public PositiveInteger(int value) {
        setValue(value);
    }

    public PositiveInteger(String value) {
        setValue(Integer.parseInt(value));
    }

    public boolean isPrime() {
        if (this.value == 1) {
            return false;
        }

        for (int i = 2; i * i <= this.value; i++) {
            if (this.value % i == 0) {
                return false;
            }
        }

        return true;
    }

    public int getValue()  {
        return this.value;
    }

    public void setValue(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be positive integer");
        }

        this.value = value;
    }
}