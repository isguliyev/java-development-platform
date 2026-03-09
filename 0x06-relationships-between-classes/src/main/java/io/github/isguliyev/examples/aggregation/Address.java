package io.github.isguliyev.examples.aggregation;

public class Address {
    private final String street;
    private final String number;
    private final String complement;
    private final String district;
    private final String city;
    private final String state;
    private final String country;

    public Address(
        String street,
        String number,
        String complement,
        String district,
        String city,
        String state,
        String country
    ) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [street=%s, number=%s, complement=%s, district=%s, city=%s, state=%s, country=%s]",
            this.getClass().getSimpleName(),
            this.street,
            this.number,
            this.complement,
            this.district,
            this.city,
            this.state,
            this.country
        );
    }

    public String getStreet() {
        return this.street;
    }

    public String getNumber() {
        return this.number;
    }

    public String getComplement() {
        return this.complement;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getCountry() {
        return this.country;
    }
}