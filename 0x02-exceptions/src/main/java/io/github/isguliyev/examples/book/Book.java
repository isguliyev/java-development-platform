package io.github.isguliyev.examples.book;

import java.math.BigDecimal;

public class Book {
    private final String title;
    private final String author;
    private final BigDecimal price;

    public Book(String title, String author, BigDecimal price) {
        if (title == null) {
            throw new NullPointerException("Title must not be null");
        }

        if (title.isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty");
        }

        if (author == null) {
            throw new NullPointerException("Author must not be null");
        }

        if (author.isEmpty()) {
            throw new IllegalArgumentException("Author must not be empty");
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must not be less than 0");
        }

        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [title=%s, author=%s, price=%s]",
            this.getClass().getSimpleName(),
            this.title,
            this.author,
            this.price
        );
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}