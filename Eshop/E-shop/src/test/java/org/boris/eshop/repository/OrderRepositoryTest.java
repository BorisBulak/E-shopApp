package org.boris.eshop.repository;

import org.boris.eshop.model.repoorder.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void shouldSaveOrder() {
        Order order = new Order();
        order.setOrderedQuantity(5L);
        order.setOrderedItem("Siberia Slim");
        order.setCustomerEmail("test@test.sk");

        Order savedOrder = orderRepository.save(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getOrderedQuantity()).isEqualTo(5L);
        assertThat(savedOrder.getOrderedItem()).isEqualTo("Siberia Slim");
    }

    @Test
    void shouldFindOrderById() {
        Order order = new Order();
        order.setOrderedQuantity(5L);
        order.setOrderedItem("Siberia Slim");
        order.setCustomerEmail("test@test.sk");

        Order savedOrder = orderRepository.save(order);

        Optional<Order> orderById = orderRepository.findById(Math.toIntExact(savedOrder.getId()));

        assertThat(orderById).isPresent();
        assertThat(orderById.get().getOrderedQuantity()).isEqualTo(5L);
        assertThat(orderById.get().getOrderedItem()).isEqualTo("Siberia Slim");


    }

    @Test
    void shouldNotFindOrderById() {
        Order order = new Order();
        order.setOrderedQuantity(5L);
        order.setOrderedItem("Siberia Slim");
        order.setCustomerEmail("test@test.sk");

        orderRepository.save(order);

        Optional<Order> orderById = orderRepository.findById(12);

        assertThat(orderById).isNotPresent();
    }

    @Test
    void shouldUpdateOrder() {
        Order order = new Order();
        order.setOrderedQuantity(5L);
        order.setOrderedItem("Siberia Slim");
        order.setCustomerEmail("test@test.sk");

        Order savedOrder = orderRepository.save(order);

        savedOrder.setOrderedQuantity(6L);

        orderRepository.save(savedOrder);

        Optional<Order> changedOrder = orderRepository.findById(Math.toIntExact(savedOrder.getId()));
        assertThat(changedOrder).isPresent();
        assertThat(changedOrder.get().getOrderedQuantity()).isEqualTo(6L);


    }

    @Test
    void shouldDeleteOrder() {
        Order order = new Order();
        order.setOrderedQuantity(5L);
        order.setOrderedItem("Siberia Slim");
        order.setCustomerEmail("test@test.sk");

        Order savedOrder = orderRepository.save(order);

        orderRepository.deleteById(Math.toIntExact(savedOrder.getId()));

        Optional<Order> deletedOrder = orderRepository.findById(Math.toIntExact(savedOrder.getId()));

        assertThat(deletedOrder).isNotPresent();
    }



}