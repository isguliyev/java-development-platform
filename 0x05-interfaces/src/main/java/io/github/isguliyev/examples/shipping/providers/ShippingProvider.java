package io.github.isguliyev.examples.shipping.providers;

import io.github.isguliyev.examples.shipping.Shipping;

import java.math.BigDecimal;

public interface ShippingProvider {
    Shipping calculateShipping(BigDecimal weight, BigDecimal price);
    ShippingProviderType getShippingProviderType();
}