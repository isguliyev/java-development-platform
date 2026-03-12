package io.github.isguliyev.examples.media;

public class Book extends Media {
    private final int edition;

    public Book(String name, int edition) {
        super(name);
        this.edition = edition;
    }

    public int getEdition() {
        return this.edition;
    }
}