package org.boris.eshop.repository;

import org.boris.eshop.model.repoorder.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public Optional<Order> findByCustomerEmail(String CustomerEmail);
}
