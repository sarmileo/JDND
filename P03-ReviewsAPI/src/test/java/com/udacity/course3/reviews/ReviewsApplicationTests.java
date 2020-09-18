package com.udacity.course3.reviews;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createProduct() {
		Product door = new Product(5,"door");
		productRepository.save(door);
		Optional<Product> doorToTest = productRepository.findById(5);

		if (doorToTest.isPresent()) {
			assertEquals("door", doorToTest.get().getName());
		}
	}

	@Test
	public void findProduct() {
		Optional<Product> book = productRepository.findById(1);

		if (book.isPresent()) {
			assertEquals("book", book.get().getName());
		}
	}

	@Test(expected=NoSuchElementException.class)
	public void deleteProduct() {
		Optional<Product> book = productRepository.findById(1);

		if (book.isPresent()) {
			productRepository.delete(book.get());
		}

		Optional<Product> bookAfterDelete = productRepository.findById(1);

		if (!bookAfterDelete.isPresent()) {
			String name = bookAfterDelete.get().getName();
		}
	}

}