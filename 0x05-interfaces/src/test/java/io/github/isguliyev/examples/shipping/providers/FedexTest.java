package io.github.isguliyev.examples.shipping.providers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

public class FedexTest {
    private final BigDecimal weightThresholdGrams = Fedex.WEIGHT_THRESHOLD_GRAMS;
    private final BigDecimal shippingRateAboveThreshold = Fedex.SHIPPING_RATE_ABOVE_THRESHOLD;
    private final BigDecimal shippingRateBelowThreshold = Fedex.SHIPPING_RATE_BELOW_THRESHOLD;

    private final Fedex fedex = new Fedex();

    @Test
    void calculateShipping_returnsShipping_whenWeightIsBelowThreshold() {
        final BigDecimal price = BigDecimal.ONE;

        final BigDecimal expectedShippingCost = price.multiply(this.shippingRateBelowThreshold);
        final BigDecimal actualShippingCost = this.fedex.calculateShipping(
            weightThresholdGrams.divide(BigDecimal.valueOf(2L)),
            price
        ).getShippingCost();

        assertEquals(expectedShippingCost, actualShippingCost);
    }

    @Test
    void calculateShipping_returnsShipping_whenWeightIsAboveThreshold() {
        final BigDecimal price = BigDecimal.ONE;

        final BigDecimal expectedShippingCost = price.multiply(this.shippingRateAboveThreshold);
        final BigDecimal actualShippingCost = this.fedex.calculateShipping(
            weightThresholdGrams.multiply(BigDecimal.valueOf(2L)),
            price
        ).getShippingCost();

        assertEquals(expectedShippingCost, actualShippingCost);
    }


    @Test
    void calculateShipping_returnsShipping_whenWeightEqualsThreshold() {
        final BigDecimal price = BigDecimal.ONE;

        final BigDecimal expectedShippingCost = price.multiply(this.shippingRateBelowThreshold);
        final BigDecimal actualShippingCost = this.fedex.calculateShipping(
            weightThresholdGrams,
            price
        ).getShippingCost();

        assertEquals(expectedShippingCost, actualShippingCost);
    }
}