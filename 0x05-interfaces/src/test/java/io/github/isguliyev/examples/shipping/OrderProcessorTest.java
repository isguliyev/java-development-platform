package io.github.isguliyev.examples.shipping;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import io.github.isguliyev.examples.shipping.providers.Fedex;
import io.github.isguliyev.examples.shipping.providers.ShippingProviderType;

public class OrderProcessorTest {
    @Test
    void process_setsShippingFieldOfOrder_whenOrderIsNotNull() {
        Fedex fedex = new Fedex();
        OrderProcessor orderProcessor = new OrderProcessor(fedex);
        Order order = new Order(
            "000000000000",
            BigDecimal.valueOf(1000L),
            BigDecimal.valueOf(10L)
        );

        orderProcessor.process(order);

        Shipping shipping = fedex.calculateShipping(order.getWeight(), order.getPrice());

        final BigDecimal expectedShippingCost = shipping.getShippingCost();
        final BigDecimal shippingCost = order.getShipping().getShippingCost();

        final ShippingProviderType expectedShippingProviderType = shipping.getShippingProviderType();
        final ShippingProviderType shippingProviderType = order.getShipping().getShippingProviderType();

        assertAll(
            () -> assertEquals(expectedShippingCost, shippingCost),
            () -> assertEquals(expectedShippingProviderType, shippingProviderType)
        );
    }
}