package io.github.isguliyev.examples.product;

import java.util.List;
import java.util.ArrayList;

public class ProductSearch {
    public static List<Product> filter(List<Product> products, FilterCriteria filterCriteria) {
        final List<Product> filteredProducts = new ArrayList<Product>();

        for (Product product : products) {
            if (filterCriteria.test(product)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }
}