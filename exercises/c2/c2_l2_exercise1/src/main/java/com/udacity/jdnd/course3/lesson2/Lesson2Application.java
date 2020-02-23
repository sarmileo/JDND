package com.udacity.jdnd.course3.lesson2;

import com.udacity.jdnd.course3.lesson2.entity.Order;
import com.udacity.jdnd.course3.lesson2.entity.OrderItem;
import com.udacity.jdnd.course3.lesson2.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
public class Lesson2Application {

	private static final Logger log = LoggerFactory.getLogger(Lesson2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Lesson2Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(OrderRepository repository) {
		return (args) -> {
			// save a couple of customers
			log.info("Saving a couple of customers' orders");

			Order order = new Order();
			order.setCustomerName("Michael Jourdain");
			order.setCustomerAddress("5th Ave, FL, 97999");
			order.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));

			// create order item1
			OrderItem item1 = new OrderItem();
			item1.setItemName("nice car");
			item1.setItemCount(1);
			//item1.setOrder(order); // *
			order.setOrderItem(item1);

			// create order item2
			OrderItem item2 = new OrderItem();
			item2.setItemName("big house");
			item2.setItemCount(2);
			//item2.setOrder(order); // *
			order.setOrderItem(item2);

			//order.setOrderItems(Arrays.asList(item1,item2)); // * without using helper method

			order = repository.save(order);

			System.err.println("Order save with Id: " + order.getOrderId());

			// To find one customer with that name
			//Optional<Order> OrderRead = repository.findByCustomerName("Jonny Deep");

			// In case we find more than one customer with the same name
			String name = "Leo B";
			Optional<List<Order>> OrderRead = repository.findAllByCustomerName(name);
			OrderRead.ifPresent(theOrders -> {
				theOrders.forEach(orderFound -> {
					System.err.println("Order: " + orderFound);
					orderFound.getOrderItems().forEach(orderItem -> System.err.println(orderItem));
					System.out.println();
				});
			});

			if (!OrderRead.isPresent())
			{
				System.err.println("Customer with name: " + name + ", does not exist in database");
			}

		};
	}
}
