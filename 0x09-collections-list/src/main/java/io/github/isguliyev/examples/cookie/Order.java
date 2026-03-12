package io.github.isguliyev.examples.cookie;

import java.util.List;
import java.util.ArrayList;

public class Order {
    private final List<CookieOrder> cookieOrders;

    public Order() {
        this.cookieOrders = new ArrayList<CookieOrder>();
    }

    public Order(List<CookieOrder> cookieOrders) {
        this.cookieOrders = new ArrayList<CookieOrder>(cookieOrders);
    }

    public int removeFlavor(String flavor) {
        int removedBoxCount = 0;

        List<CookieOrder> ordersToRemove = new ArrayList<CookieOrder>();

        for (CookieOrder cookieOrder : this.cookieOrders) {
            if (cookieOrder.getFlavor().equals(flavor)) {
                ordersToRemove.add(cookieOrder);

                removedBoxCount += cookieOrder.getBoxCount();
            }
        }

        this.cookieOrders.removeAll(ordersToRemove);

        return removedBoxCount;
    }

    public int getTotalBoxCount() {
        int totalBoxCount = 0;

        for (CookieOrder cookieOrder : this.cookieOrders) {
            totalBoxCount += cookieOrder.getBoxCount();
        }

        return totalBoxCount;
    }

    public void addCookieOrder(CookieOrder cookieOrder) {
        this.cookieOrders.add(cookieOrder);
    }

    public List<CookieOrder> getCookieOrders() {
        return this.cookieOrders;
    }
}