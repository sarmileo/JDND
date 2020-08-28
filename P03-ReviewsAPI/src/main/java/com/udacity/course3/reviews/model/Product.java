package com.udacity.course3.reviews.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "product")
public class Product
{
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @Id
    @Column(name= "productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name= "productName")
    String productName;

    public Product()
    {
    }

    public Product(Set<Review> reviews, String productName)
    {
        this.reviews = reviews;
        this.productName = productName;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
