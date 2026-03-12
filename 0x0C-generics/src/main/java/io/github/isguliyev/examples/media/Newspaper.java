package io.github.isguliyev.examples.media;

public class Newspaper extends Media {
    private final int articleCount;

    public Newspaper(String name, int articleCount) {
        super(name);
        this.articleCount = articleCount;
    }

    public int getArticleCount() {
        return this.articleCount;
    }
}