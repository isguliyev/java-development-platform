package io.github.isguliyev.examples.shipping.providers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

public class DalseyHillblomLynnTest {
    private final BigDecimal weightThresholdGrams = DalseyHillblomLynn.WEIGHT_THRESHOLD_GRAMS;
    private final BigDecimal shippingRateAboveThreshold = DalseyHillblomLynn.SHIPPING_RATE_ABOVE_THRESHOLD;
    private final BigDecimal shippingRateBelowThreshold = DalseyHillblomLynn.SHIPPING_RATE_BELOW_THRESHOLD;

    private final DalseyHillblomLynn dalseyHillblomLynn = new DalseyHillblomLynn();

    @Test
    void calculateShipping_returnsShipping_whenWeightIsBelowThreshold() {
        final BigDecimal price = BigDecimal.ONE;

        final BigDecimal expectedShippingCost = price.multiply(this.shippingRateBelowThreshold);
        final BigDecimal actualShippingCost = this.dalseyHillblomLynn
            .calculateShipping(weightThresholdGrams.divide(BigDecimal.valueOf(2L)), price)
            .getShippingCost();

        assertEquals(expectedShippingCost, actualShippingCost);
    }

    @Test
    void calculateShipping_returnsShipping_whenWeightIsAboveThreshold() {
        final BigDecimal price = BigDecimal.ONE;

        final BigDecimal expectedShippingCost = price.multiply(this.shippingRateAboveThreshold);
        final BigDecimal actualShippingCost = dalseyHillblomLynn
            .calculateShipping(weightThresholdGrams.multiply(BigDecimal.valueOf(2L)), price)
            .getShippingCost();

        assertEquals(expectedShippingCost, actualShippingCost);
    }


    @Test
    void calculateShipping_returnsShipping_whenWeightEqualsThreshold() {
        final BigDecimal price = BigDecimal.ONE;

        final BigDecimal expectedShippingCost = price.multiply(this.shippingRateBelowThreshold);
        final BigDecimal actualShippingCost = dalseyHillblomLynn
            .calculateShipping(weightThresholdGrams, price)
            .getShippingCost();

        assertEquals(expectedShippingCost, actualShippingCost);
    }
}