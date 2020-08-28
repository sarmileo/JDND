package com.udacity.course3.reviews.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "review")
public class Review
{
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "text")
    private String text;

    @Column(name= "title")
    private String title;

    public Review()
    {
    }

    public Review(Set<Comment> comments, Product product, String text, String title)
    {
        this.comments = comments;
        this.product = product;
        this.text = text;
        this.title = title;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
