package io.github.isguliyev.examples.product;

import java.math.BigDecimal;

import java.util.Objects;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final double weight;
    private final int stockQuantity;
    private final ProductType type;

    public Product(
        String name,
        BigDecimal price,
        double weight,
        int stockQuantity,
        ProductType type
    ) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.stockQuantity = stockQuantity;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [name=%s, price=%s, weight=%.2f, stockQuantity=%d, type=%s]",
            this.getClass().getSimpleName(),
            this.name,
            this.price,
            this.weight,
            this.stockQuantity,
            this.type
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Product product)) {
            return false;
        }

        return Objects.equals(this.name, product.name)
            && Objects.equals(this.price, product.price)
            && this.weight == product.weight
            && this.stockQuantity == product.stockQuantity
            && this.type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.price, this.weight, this.stockQuantity, this.type);
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getStockQuantity() {
        return this.stockQuantity;
    }

    public ProductType getType() {
        return this.type;
    }
}