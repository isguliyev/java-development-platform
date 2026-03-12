package io.github.isguliyev.examples.media;

public abstract class Media {
    private final String name;

    public Media(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}