package io.github.isguliyev.examples.shipping;

import io.github.isguliyev.examples.shipping.providers.Fedex;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor(new Fedex());

        Order order = new Order(
            "000000000000",
            BigDecimal.valueOf(1000L),
            BigDecimal.valueOf(10L)
        );

        orderProcessor.process(order);
    }
}