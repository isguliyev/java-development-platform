package io.github.isguliyev.examples.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;

import java.math.BigDecimal;

public class ProductSearchTest {
    @Test
    void filter_filtersProductList() {
        final List<Product> products = List.of(
            new Product("Laptop", BigDecimal.valueOf(1200.0d), 1.8d, 15, ProductType.ELECTRONIC),
            new Product("Coffee Maker", BigDecimal.valueOf(49.99d), 3.0d, 20, ProductType.KITCHEN),
            new Product("Remote Car", BigDecimal.valueOf(49.99d), 1.2d, 25, ProductType.TOY)
        );

        final List<Product> expectedProducts = List.of(
            new Product("Laptop", BigDecimal.valueOf(1200.0d), 1.8d, 15, ProductType.ELECTRONIC),
            new Product("Coffee Maker", BigDecimal.valueOf(49.99d), 3.0d, 20, ProductType.KITCHEN)
        );

        final List<Product> actualProducts = ProductSearch.filter(
            products,
            (product) ->
            product.getType() == ProductType.ELECTRONIC || product.getType() == ProductType.KITCHEN
        );

        assertEquals(expectedProducts, actualProducts);
    }
}