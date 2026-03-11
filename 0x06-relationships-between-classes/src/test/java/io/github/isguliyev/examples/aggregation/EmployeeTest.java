package io.github.isguliyev.examples.aggregation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {
    @Test
    void constructor_setsAllFieldsCorrectly() {
        final String expectedName = "Gman";
        final int expectedCode = 0;
        final String expectedStreet = "unknown";
        final String expectedNumber = "unknown";
        final String expectedComplement = "unknown";
        final String expectedDistrict = "unknown";
        final String expectedCity = "unknown";
        final String expectedState = "unknown";
        final String expectedCountry = "unknown";

        Employee employee = new Employee(
            expectedName,
            expectedCode,
            new Address(
                expectedStreet,
                expectedNumber,
                expectedComplement,
                expectedDistrict,
                expectedCity,
                expectedState,
                expectedCountry
            )
        );

        Address address = employee.getAddress();

        assertAll(
            () -> assertEquals(expectedName, employee.getName()),
            () -> assertEquals(expectedCode, employee.getCode()),
            () -> assertEquals(expectedStreet, address.getStreet()),
            () -> assertEquals(expectedNumber, address.getNumber()),
            () -> assertEquals(expectedComplement, address.getComplement()),
            () -> assertEquals(expectedDistrict, address.getDistrict()),
            () -> assertEquals(expectedCity, address.getCity()),
            () -> assertEquals(expectedState, address.getState()),
            () -> assertEquals(expectedCountry, address.getCountry())
       );
    }
}