package com.udacity.course3.reviews.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment
{
    @ManyToOne
    @JoinColumn(name = "reviewId")
    private Review review;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    public Comment()
    {
    }

    public Comment(Review review, String content, int reviewId)
    {
        this.review = review;
        this.content = content;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
