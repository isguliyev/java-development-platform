package io.github.isguliyev.examples.shipping.providers;

import io.github.isguliyev.examples.shipping.Shipping;

import java.math.BigDecimal;

public class UnitedParcelService implements ShippingProvider {
    public static final BigDecimal WEIGHT_THRESHOLD_GRAMS = BigDecimal.valueOf(2000.0d);
    public static final BigDecimal SHIPPING_RATE_ABOVE_THRESHOLD = BigDecimal.valueOf(0.07d);
    public static final BigDecimal SHIPPING_RATE_BELOW_THRESHOLD = BigDecimal.valueOf(0.045d);

    public Shipping calculateShipping(BigDecimal weight, BigDecimal price) {
        return new Shipping(
            price.multiply(
                weight.compareTo(WEIGHT_THRESHOLD_GRAMS) > 0
                ? SHIPPING_RATE_ABOVE_THRESHOLD : SHIPPING_RATE_BELOW_THRESHOLD
            ),
            ShippingProviderType.UPS
        );
    }

    public ShippingProviderType getShippingProviderType() {
        return ShippingProviderType.UPS;
    }
}