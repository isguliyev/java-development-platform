package io.github.isguliyev.examples.shipping;

import io.github.isguliyev.examples.shipping.providers.ShippingProviderType;

import java.math.BigDecimal;

public class Shipping {
    private final BigDecimal shippingCost;
    private final ShippingProviderType shippingProviderType;

    public Shipping(BigDecimal shippingCost, ShippingProviderType shippingProviderType) {
        this.shippingCost = shippingCost;
        this.shippingProviderType = shippingProviderType;
    }

    public BigDecimal getShippingCost() {
        return this.shippingCost;
    }

    public ShippingProviderType getShippingProviderType() {
        return this.shippingProviderType;
    }
}