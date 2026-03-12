package io.github.isguliyev.examples.cookie;

import java.util.Objects;

public class CookieOrder {
    private final String flavor;
    private final int boxCount;

    public CookieOrder(String flavor, int boxCount) {
        this.flavor = flavor;
        this.boxCount = boxCount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof CookieOrder cookieOrder)) {
            return false;
        }

        return this.boxCount == cookieOrder.boxCount && Objects.equals(this.flavor, cookieOrder.flavor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.flavor, this.boxCount);
    }

    public String getFlavor() {
        return this.flavor;
    }

    public int getBoxCount() {
        return this.boxCount;
    }
}