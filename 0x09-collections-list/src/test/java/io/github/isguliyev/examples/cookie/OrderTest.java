package io.github.isguliyev.examples.cookie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class OrderTest {
    @Test
    void removeFlavor_removesOrdersWithMatchingFlavor_whenFlavorIsNotNull() {
        final String flavorToRemove = "Cinnamon Roll";

        List<CookieOrder> initialCookieOrders = List.of(
            new CookieOrder(flavorToRemove, 10),
            new CookieOrder(flavorToRemove, 10),
            new CookieOrder("Hidden Mint", 5),
            new CookieOrder("Hidden Mint", 5),
            new CookieOrder("Hidden Mint", 5)
        );

        List<CookieOrder> expectedCookieOrders = List.of(
            new CookieOrder("Hidden Mint", 5),
            new CookieOrder("Hidden Mint", 5),
            new CookieOrder("Hidden Mint", 5)
        );

        Order order = new Order(initialCookieOrders);

        order.removeFlavor(flavorToRemove);

        assertEquals(expectedCookieOrders, order.getCookieOrders());
    }

    @Test
    void removeFlavor_returnsRemovedBoxCount_whenFlavorIsNotNull() {
        final String flavorToRemove = "Cinnamon Roll";

        List<CookieOrder> initialCookieOrders = List.of(
            new CookieOrder(flavorToRemove, 10),
            new CookieOrder(flavorToRemove, 10),
            new CookieOrder("Hidden Mint", 5),
            new CookieOrder("Hidden Mint", 5),
            new CookieOrder("Hidden Mint", 5)
        );

        final int expectedRemovedBoxCount = initialCookieOrders.stream().reduce(
            0,
            (subtotal, element) -> {
                if (element.getFlavor().equals(flavorToRemove)) {
                    return subtotal + element.getBoxCount();
                }

                return subtotal;
            },
            Integer::sum
        );

        Order order = new Order(initialCookieOrders);

        assertEquals(expectedRemovedBoxCount, order.removeFlavor(flavorToRemove));
    }

    @Test
    void getTotalBoxCount_returnsTotalBoxCount() {
        List<CookieOrder> initialCookieOrders = List.of(
            new CookieOrder("Cinnamon Roll", 10),
            new CookieOrder("Cinnamon Roll", 10),
            new CookieOrder("Hidden Mint", 5),
            new CookieOrder("Hidden Mint", 5),
            new CookieOrder("Hidden Mint", 5)
        );

        final int expectedTotalBoxCount = initialCookieOrders.stream().reduce(
            0,
           (subtotal, element) -> subtotal + element.getBoxCount(),
           Integer::sum
        );

        Order order = new Order(initialCookieOrders);

        assertEquals(expectedTotalBoxCount, order.getTotalBoxCount());
    }
}