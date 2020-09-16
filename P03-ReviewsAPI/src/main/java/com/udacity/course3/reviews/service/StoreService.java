package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.domain.Store;
import com.udacity.course3.reviews.domain.Unit;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreService {
    @Autowired
    private final ProductRepository productRepository;

    public StoreService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Store getStoreProducts() {
        Iterable<Product> products = productRepository.findAll();
        Store store = new Store();
        List<Optional<Unit>> productsList = new ArrayList<>();

        products.forEach(product -> {
            UnitService unitService = new UnitService(productRepository);
            productsList.add(unitService.getUnitInformation(product.getId()));
        });

        store.setProducts(productsList);

        return store;
    }
}
