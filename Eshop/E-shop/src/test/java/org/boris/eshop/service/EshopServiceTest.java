package org.boris.eshop.service;

import org.boris.eshop.mapper.OrderMapper;
import org.boris.eshop.model.repoorder.Order;
import org.boris.eshop.model.request.CustomerOrder;
import org.boris.eshop.rabbitmq.Producer;
import org.boris.eshop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EshopServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private Producer producer;

    @InjectMocks
    private EshopService service;



    @Test
    void shouldThrowExceptionWhenMapperFails(){
        CustomerOrder customerOrder = new CustomerOrder();

        when(orderMapper.toOrder(customerOrder)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            service.createOrder(customerOrder);
        });


        verify(orderRepository,never()).save(any());
    }


    @Test
    void shouldCreateOrder(){
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomerEmail("test@tes.sk");
        customerOrder.setOrderedQuantity(5L);
        customerOrder.setOrderedItem("Siberia Slim");

        Order order = new Order();
        order.setCustomerEmail(customerOrder.getCustomerEmail());
        order.setOrderedItem(customerOrder.getOrderedItem());
        order.setOrderedQuantity(customerOrder.getOrderedQuantity());



        when(orderMapper.toOrder(customerOrder)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);

        service.createOrder(customerOrder);

        verify(orderMapper).toOrder(customerOrder);
        verify(orderRepository).save(order);
        verify(producer).sendOrder(any());
    }


}