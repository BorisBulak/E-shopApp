package org.boris.eshop.service;

import org.boris.eshop.mapper.OrderMapper;
import org.boris.eshop.model.repoorder.Order;
import org.boris.eshop.model.request.CustomerOrder;
import org.boris.eshop.rabbitmq.Producer;
import org.boris.eshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class EshopService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final Producer producer;



    public EshopService(OrderRepository orderRepository, OrderMapper orderMapper, Producer producer) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.producer = producer;
    }

    public void createOrder(CustomerOrder customerOrder) {
        Order order = orderMapper.toOrder(customerOrder);
        orderRepository.save(order);

        producer.sendOrder(orderMapper.toEventOrder(order));

    }
}
