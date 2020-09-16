package com.udacity.course3.reviews.domain;

import java.util.List;
import java.util.Optional;

public class Store {
    private List<Optional<Unit>> products;

    public List<Optional<Unit>> getProducts() {
        return products;
    }

    public void setProducts(List<Optional<Unit>> products) {
        this.products = products;
    }
}
