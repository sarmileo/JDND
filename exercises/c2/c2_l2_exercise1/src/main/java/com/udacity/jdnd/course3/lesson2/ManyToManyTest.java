package com.udacity.jdnd.course3.lesson2;

import com.udacity.jdnd.course3.lesson2.entity.Author;
import com.udacity.jdnd.course3.lesson2.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ManyToManyTest {

    // Persistence unit Order also includes Author and Book
    private static final String PERSISTENCE_UNIT_NAME = "Order";

    private static EntityManagerFactory bookFactory;

    public static void main(String[] args) {
        // STEP 1: Create a factory for the persistence unit
        bookFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        // STEP 2: Create an EntityManager
        EntityManager entityManager = bookFactory.createEntityManager();

        // STEP 3: Start a transaction
        entityManager.getTransaction().begin();

        // STEP 4: Create books (entity is in Transient state)
        Book book1 = new Book();
        book1.setTitle("El Marti que yo conoci");
        book1.setIsbn("book1isbn");

        Book book2 = new Book();
        book2.setTitle("Mentes Rapidas");
        book2.setIsbn("book2isbn");

        // create author for book1
        Author book1Author1 = new Author();
        book1Author1.setName("book1Julio");

        book1.addAuthor(book1Author1);

        // create author for book2
        Author book2Author1 = new Author();
        book2Author1.setName("book2Ramon");

        book2.addAuthor(book2Author1);

        Set<Author> book2Authors = new HashSet<>(Arrays.asList(
                new Author("book2Pepe"),
                new Author("book2Mario"),
                new Author("book2Leo")
        ));
        book2.addAuthors(book2Authors);

        // STEP 5: Persist the book entity
        entityManager.persist(book1);
        entityManager.persist(book2);

        // entity is persistent now
        entityManager.getTransaction().commit();

        entityManager.close();

        // Read a book
        readBook(book1.getBookId(), bookFactory);
        readBook(book2.getBookId(), bookFactory);

        // Delete a book
        deleteBook(6, bookFactory);

        bookFactory.close();

    }

    private static void readBook(Integer bookId, EntityManagerFactory factory) {
        // STEP 1: Create an EntityManager
        EntityManager entityManager = factory.createEntityManager();

        // STEP 2: use the find() method to load a book
        Optional<Book> book = Optional.ofNullable(entityManager.find(Book.class, bookId));

        book.ifPresent(theBook -> {
            System.err.println("Book FOUND: " + theBook.getTitle());
            theBook.getAuthors().forEach(System.err::println);
        });

        if (!book.isPresent())
        {
            System.err.println("Book with Id: " + bookId + " NOT FOUND");
        }

        entityManager.close();

        // TIP: use em.remove() to delete the corresponding row of the entity from the table

    }

   private static void deleteBook(Integer bookId, EntityManagerFactory factory) {
       // STEP 1: Create an EntityManager
       EntityManager em = factory.createEntityManager();

       // STEP 2: use the find() method to load a book
       Optional<Book> book = Optional.ofNullable(em.find(Book.class, bookId));
       book.ifPresent(theBook -> {
           em.getTransaction().begin();

           em.remove(theBook);
           System.err.println("Book : " + theBook + " was Removed from database");

           em.getTransaction().commit();
       });

       if (!book.isPresent())
       {
           System.err.println("Book with Id: " + bookId + " does not exist in the database");
       }

       em.close();
   }
}