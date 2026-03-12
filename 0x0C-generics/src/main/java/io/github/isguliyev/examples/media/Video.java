package io.github.isguliyev.examples.media;

public class Video extends Media {
    private final int duration;

    public Video(String name, int duration) {
        super(name);
        this.duration = duration;
    }

    public int getDuration() {
        return this.duration;
    }
}