package io.github.isguliyev.examples.product;

@FunctionalInterface
public interface FilterCriteria {
    boolean test(Product product);
}