package com.udacity.jdnd.course3.lesson2;

import com.udacity.jdnd.course3.lesson2.entity.Order;
import com.udacity.jdnd.course3.lesson2.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class Lesson2ApplicationTests {

	@Autowired
	private OrderRepository repository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void shouldFindAllOrders()
	{
		Iterable<Order> orders =  repository.findAll();

		int ordersTotal = 1;

		assertThat(orders).hasSize(ordersTotal);
	}

	@Test
	public void shouldFindCustomerByName()
	{
		Optional<List<Order>> listOptional = repository.findAllByCustomerName("Michael Jourdain");

		if (listOptional.isPresent())
		{
			assertThat(listOptional.get().size()).isEqualTo(1);
		}
	}

	@Test
	public void shouldFailIfCustomerNameNotExist()
	{
		Optional<List<Order>> listOptional = repository.findAllByCustomerName("Michael");

		assertThat(listOptional.isPresent()).isFalse();
	}

	@Test
	public void shouldFindCustomerOrderItems()
	{
		Optional<Order> orderOptional = repository.findById(1);

		if (orderOptional.isPresent())
		{
			int itemsTotal = orderOptional.get().getOrderItems().size();
			assertThat(itemsTotal).isEqualTo(2);
		}
	}

}
