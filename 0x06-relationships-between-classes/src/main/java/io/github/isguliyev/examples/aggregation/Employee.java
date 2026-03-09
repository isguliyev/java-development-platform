package io.github.isguliyev.examples.aggregation;

public class Employee {
    private final String name;
    private final int code;
    private final Address address;

    public Employee(String name, int code, Address address) {
        this.name = name;
        this.code = code;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [name=%s, code=%s, address=%s]",
            this.getClass().getSimpleName(),
            this.name,
            this.code,
            this.address
        );
    }

    public String getName() {
        return this.name;
    }

    public int getCode() {
        return this.code;
    }

    public Address getAddress() {
        return this.address;
    }
}