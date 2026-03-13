package io.github.isguliyev.examples.mobile;

import java.util.Objects;

public class Contact {
    final private String name;
    final private String phoneNumber;
    final private NumberType type;

    public Contact(String name, String phoneNumber, NumberType type) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Contact contact)) {
            return false;
        }

        final boolean areNamesEqual = Objects.equals(this.name, contact.name);
        final boolean arePhoneNumbersEqual = Objects.equals(this.phoneNumber, contact.phoneNumber);
        final boolean areTypesEqual = this.type == contact.type;;

        return areNamesEqual && arePhoneNumbersEqual && areTypesEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.phoneNumber, this.type);
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public NumberType getType() {
        return this.type;
    }
}