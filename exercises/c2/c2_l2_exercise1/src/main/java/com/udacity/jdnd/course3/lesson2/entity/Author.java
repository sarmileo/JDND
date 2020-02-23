package com.udacity.jdnd.course3.lesson2.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
    //private List<Book> authors = new ArrayList<>();


    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, Set<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Book> getBooks() {
//        return authors;
//    }
    public Set<Book> getBooks() {
        return books;
    }

//    public void setAuthors(List<Book> authors) {
//        this.authors = authors;
//    }
    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    // utility method to manage association
    public void addBook(Book book)
    {
        this.books.add(book);
        book.getAuthors().add(this);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", books=" + books +
                '}';
    }
}
