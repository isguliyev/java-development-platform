package io.github.isguliyev.examples.cookie;

public class CookieOrder {
    private final String flavor;
    private final int boxQuantity;

    public CookieOrder(String flavor, int boxQuantity) {
        this.flavor = flavor;
        this.boxQuantity = boxQuantity;
    }

    public String getFlavor() {
        return this.flavor;
    }

    public int getBoxQuantity() {
        return this.boxQuantity;
    }
}