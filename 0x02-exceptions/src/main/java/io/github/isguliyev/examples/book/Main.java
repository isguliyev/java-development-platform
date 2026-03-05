package io.github.isguliyev.examples.book;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Book donQuixote = new Book(
            "Don Quixote",
            "Miguel de Cervantes",
            BigDecimal.valueOf(19.99d)
        );

        Book countMonteCristo = new Book(
            "The Count of Monte Cristo",
            "Alexandre Dumas",
            BigDecimal.valueOf(19.99d)
        );

        Book threeMusketeers = new Book(
            "The Three Musketeers",
            "Alexandre Dumas",
            BigDecimal.valueOf(19.99d)
        );

        System.out.println(donQuixote);
        System.out.println(countMonteCristo);
        System.out.println(threeMusketeers);
    }
}