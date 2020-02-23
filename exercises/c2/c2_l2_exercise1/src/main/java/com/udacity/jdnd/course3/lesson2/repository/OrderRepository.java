package com.udacity.jdnd.course3.lesson2.repository;

import com.udacity.jdnd.course3.lesson2.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    Optional<Order> findByCustomerName(String customerName);

    Optional<List<Order>> findAllByCustomerName(String customerName);
}