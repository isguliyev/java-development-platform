package io.github.isguliyev.examples.serialization;

import java.io.Serializable;

import java.util.Objects;

public class Employee implements Serializable {
    private final String name;
    private final transient String password;

    public Employee(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Employee employee)) {
            return false;
        }

        return Objects.equals(this.name, employee.name);
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
}