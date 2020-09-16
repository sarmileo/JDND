package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.Unit;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.service.StoreService;
import com.udacity.course3.reviews.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    // TODO: Wire JPA repositories here
    @Autowired
    ProductRepository productRepository;

    /**
     * Creates a product.
     * <p>
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        productRepository.save(product);

        //throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        UnitService unitService = new UnitService(productRepository);
        Optional<Unit> unit = unitService.getUnitInformation(id);

        if (unit.isPresent()) {
            return new ResponseEntity<>(unit, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<?> listProducts() {
        StoreService storeService = new StoreService(productRepository);
        List<Optional<Unit>> productList = storeService.getStoreProducts().getProducts();

        if (!productList.isEmpty()) {
            return productList;
        }

        return Arrays.asList("Empty Store");

        //throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }
}