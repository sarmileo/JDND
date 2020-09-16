package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.domain.Unit;
import com.udacity.course3.reviews.domain.UnitReview;
import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UnitService {
    @Autowired
    private final ProductRepository productRepository;

    public UnitService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Unit> getUnitInformation(Integer id) {
        Unit unit = new Unit();
        Optional<Product> product = productRepository.findById(id);

        List<UnitReview> unitReviewList = new ArrayList<>();

        if (product.isPresent()) {
            unit.setProductName(product.get().getName());

            // Create a list of Reviews with its comments
            product.get().getReviews().forEach(review -> {
                UnitReview unitReview = new UnitReview();
                // Set title
                unitReview.setReviewTitle(review.getTitle());

                //Set Content
                unitReview.setReviewContent(review.getContent());

                List<String> comments = review.getComments().stream()
                        .map(Comment::getContent)
                        .collect(Collectors.toList());
                unitReview.setCommentsList(comments);

                unitReviewList.add(unitReview);
            });

            unit.setReviewList(unitReviewList);
        }

        return Optional.of(unit);
    }

    public static List<String> getCommentsForReview(Review review) {
        List<String> comments = review.getComments().stream()
                .map(Comment::getContent)
                .collect(Collectors.toList());

        return comments;
    }

}
