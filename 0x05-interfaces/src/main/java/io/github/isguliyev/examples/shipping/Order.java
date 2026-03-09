package io.github.isguliyev.examples.shipping;

import java.math.BigDecimal;

public class Order {
    private final String code;
    private final BigDecimal weight;
    private final BigDecimal price;
    private Shipping shipping;

    public Order(String code, BigDecimal weight, BigDecimal price) {
        this.code = code;
        this.weight = weight;
        this.price = price;
    }

    public String getCode() {
        return this.code;
    }

    public BigDecimal getWeight () {
        return this.weight;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Shipping getShipping() {
        return this.shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }
}