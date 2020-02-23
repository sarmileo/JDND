package com.udacity.jdnd.course3.lesson2;

import com.udacity.jdnd.course3.lesson2.entity.Order;
import com.udacity.jdnd.course3.lesson2.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

public class OrderTest {

    private static final String PERSISTENCE_UNIT_NAME = "Order";

    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        // STEP 1: Create a factory for the persistence unit
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        // STEP 2: Create an EntityManager
        EntityManager entityManager = factory.createEntityManager();

        // STEP 3: Start a transaction
        entityManager.getTransaction().begin();

        // STEP 4: Create an order (entity is in Transient state)
        Order order = new Order();
        order.setCustomerName("Jonny La Bala");
        order.setCustomerAddress("325 Entre las dos ceibas, Miami");
        order.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));

        // create order item1
        OrderItem item1 = new OrderItem();
        item1.setOrder(order);
        item1.setItemName("ball");
        item1.setItemCount(3);

        // create order item2
        OrderItem item2 = new OrderItem();
        item2.setOrder(order);
        item2.setItemName("bat");
        item2.setItemCount(5);

        order.setOrderItems(Arrays.asList(item1, item2));

        // STEP 5: Persist the order entity
        entityManager.persist(order);

        // NOTE: Order Item is NOT persisted here

        // entity is persistent now
        System.err.println("Order ID: " + order.getOrderId());
        System.err.println("Order item ID 1: " + item1.getOrderItemId());
        System.err.println("Order item ID 2: " + item2.getOrderItemId());

        entityManager.getTransaction().commit();

        entityManager.close();

        readOrder(order.getOrderId(), factory);

        deleteOrder(8, factory);

        factory.close();

    }

    private static void readOrder(Integer orderId, EntityManagerFactory factory) {
        // STEP 1: Create an EntityManager
        EntityManager entityManager = factory.createEntityManager();

        // STEP 2: use the find() method to load an order
        // OrderItem is fetched eagerly by using a JOIN
        Optional<Order> order = Optional.ofNullable(entityManager.find(Order.class, orderId));

        order.ifPresent(theOrder -> {
            System.err.println("Order FOUND: " + theOrder);
            theOrder.getOrderItems().forEach(System.err::println);
        });

        if (!order.isPresent())
        {
            System.err.println("Order with Id: " + orderId + " NOT FOUND");
        }

        entityManager.close();
    }

   private static void deleteOrder(Integer orderId, EntityManagerFactory factory) {
       // STEP 1: Create an EntityManager
       EntityManager entityManager = factory.createEntityManager();

       // STEP 2: use the find() method to load an order
       /* Using Optional */
       Optional<Order> order = Optional.ofNullable(entityManager.find(Order.class, orderId));
       order.ifPresent(theOrder -> {
           entityManager.getTransaction().begin();

           entityManager.remove(theOrder);

           System.err.println("Order Deleted : " + theOrder);

           entityManager.getTransaction().commit();
       });

       if (!order.isPresent())
       {
           System.err.println("Order with Id : " + orderId + " does not exist");
       }

       entityManager.close();
   }
}