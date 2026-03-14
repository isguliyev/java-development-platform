package io.github.isguliyev.examples.shipping.providers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

public class UnitedParcelServiceTest {
    private final BigDecimal weightThresholdGrams = UnitedParcelService.WEIGHT_THRESHOLD_GRAMS;
    private final BigDecimal shippingRateAboveThreshold = UnitedParcelService.SHIPPING_RATE_ABOVE_THRESHOLD;
    private final BigDecimal shippingRateBelowThreshold = UnitedParcelService.SHIPPING_RATE_BELOW_THRESHOLD;

    private final UnitedParcelService unitedParcelService = new UnitedParcelService();

    @Test
    void calculateShipping_returnsShipping_whenWeightIsBelowThreshold() {
        final BigDecimal price = BigDecimal.ONE;

        final BigDecimal expectedShippingCost = price.multiply(this.shippingRateBelowThreshold);
        final BigDecimal actualShippingCost = this.unitedParcelService
            .calculateShipping(weightThresholdGrams.divide(BigDecimal.valueOf(2L)), price)
            .getShippingCost();

        assertEquals(expectedShippingCost, actualShippingCost);
    }

    @Test
    void calculateShipping_returnsShipping_whenWeightIsAboveThreshold() {
        final BigDecimal price = BigDecimal.ONE;

        final BigDecimal expectedShippingCost = price.multiply(this.shippingRateAboveThreshold);
        final BigDecimal actualShippingCost = this.unitedParcelService
            .calculateShipping(weightThresholdGrams.multiply(BigDecimal.valueOf(2L)), price)
            .getShippingCost();

        assertEquals(expectedShippingCost, actualShippingCost);
    }

    @Test
    void calculateShipping_returnsShipping_whenWeightEqualsThreshold() {
        final BigDecimal price = BigDecimal.ONE;

        final BigDecimal expectedShippingCost = price.multiply(this.shippingRateBelowThreshold);
        final BigDecimal actualShippingCost = this.unitedParcelService
            .calculateShipping(weightThresholdGrams, price)
            .getShippingCost();

        assertEquals(expectedShippingCost, actualShippingCost);
    }
}