package io.github.isguliyev.examples.cookie;

import java.util.List;
import java.util.ArrayList;

public class Order {
    private final List<CookieOrder> cookieOrders;

    public Order() {
        this.cookieOrders = new ArrayList<CookieOrder>();
    }

    public Order(List<CookieOrder> cookieOrders) {
        this.cookieOrders = cookieOrders;
    }

    public int removeFlavor(String flavor) {
        int boxesRemoved = 0;

        List<CookieOrder> flavorsToRemove = new ArrayList<CookieOrder>();

        for (CookieOrder cookieOrder : this.cookieOrders) {
            if (cookieOrder.getFlavor().equals(flavor)) {
                flavorsToRemove.add(cookieOrder);
                boxesRemoved += cookieOrder.getBoxQuantity();
            }
        }

        this.cookies.removeAll(flavorsToRemove);

        return boxesRemoved;
    }

    public int getTotalBoxes() {
        int totalBoxes = 0;

        for (CookieOrder cookieOrder : this.cookieOrders) {
            totalBoxes += cookieOrder.getBoxQuantity();
        }

        return totalBoxes;
    }

    public void addCookieOrder(CookieOrder cookieOrder) {
        this.cookies.add(cookieOrder);
    }

    public List<CookieOrder> getCookieOrders() {
        return this.cookieOrders;
    }
}