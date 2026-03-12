package io.github.isguliyev.examples.media;

import java.util.List;
import java.util.ArrayList;

public class Library<T extends Media> {
    private final List<T> mediaCollection;

    public Library() {
        this.mediaCollection = new ArrayList<T>();
    }

    public List<T> getMediaCollection() {
        return this.mediaCollection;
    }
}