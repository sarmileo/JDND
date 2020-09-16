package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    /**
     * @Description: Just to know the query executed by findById
     * - @Query(value = "SELECT * FROM reviews.product\n" +
     * "JOIN reviews.review ON reviews.review.product_id=reviews.product.id\n" +
     * "JOIN reviews.comment ON reviews.comment.review_id=reviews.review.id\n" +
     * "WHERE reviews.review.product_id= :id", nativeQuery = true)
     */
    Optional<Product> findById(@Param("id") Integer id);
}
