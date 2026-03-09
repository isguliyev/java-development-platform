package io.github.isguliyev.examples.aggregation;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee(
            "Gman",
            0,
            new Address(
                "unknown",
                "unknown",
                "unknown",
                "unknown",
                "unknown",
                "unknown",
                "unknown"
            )
        );

        System.out.println(employee);
    }
}