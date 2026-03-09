package io.github.isguliyev.examples.shipping;

import io.github.isguliyev.examples.shipping.providers.ShippingProvider;

public class OrderProcessor {
    private final ShippingProvider shippingProvider;

    public OrderProcessor(ShippingProvider shippingProvider) {
        this.shippingProvider = shippingProvider;
    }

    public void process(Order order) {
        order.setShipping(
            this.shippingProvider.calculateShipping(
                order.getWeight(),
                order.getPrice()
            )
        );
    }

    public ShippingProvider getShippingProvider() {
        return this.shippingProvider;
    }
}