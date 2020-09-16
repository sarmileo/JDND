package com.udacity.course3.reviews.domain;

import java.util.List;

public class Unit {
    private String productName;
    private List<UnitReview> reviewList;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<UnitReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<UnitReview> reviewList) {
        this.reviewList = reviewList;
    }
}
