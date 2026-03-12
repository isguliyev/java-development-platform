package io.github.isguliyev.examples.media;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class LibraryTest {
    private Library<Media> library;

    @BeforeEach
    void setUp() {
        this.library = new Library<Media>();
    }

    @Test
    void constructor_initializesMediaCollection() {
        assertNotNull(this.library.getMediaCollection());
    }

    @Test
    void getMediaCollection_returnsMediaCollectionList() {
        final List<Media> mediaCollection = this.library.getMediaCollection();

        mediaCollection.add(new Book("Moby Dick", 1));
        mediaCollection.add(new Newspaper("The New York Times", 10));
        mediaCollection.add(new Video("Me at the zoo", 19));

        assertEquals(mediaCollection, this.library.getMediaCollection());
    }
}