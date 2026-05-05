package org.boris.inventory.rabbitmq;

import org.boris.inventory.model.EventOrder;
import org.boris.inventory.model.InventoryItem;
import org.boris.inventory.model.OrderConfirmedEvent;
import org.boris.inventory.model.OrderRejectedEvent;
import org.boris.inventory.repository.InventoryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    InventoryRepository inventoryRepository;
    RabbitTemplate rabbitTemplate;

    public Consumer(InventoryRepository inventoryRepository, RabbitTemplate rabbitTemplate) {
        this.inventoryRepository = inventoryRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitConfig.ORDER_CREATED_INVENTORY_QUEUE)
    public void receive(EventOrder eventOrder) {
        String orderedItem = eventOrder.getOrderedItem();
        InventoryItem inventoryItem = inventoryRepository.findByItemName(orderedItem);

        if (inventoryItem != null) {
            Long quantity = inventoryItem.getQuantity();
            if (quantity < eventOrder.getOrderedQuantity()) {


                OrderRejectedEvent orderRejectedEvent = new OrderRejectedEvent();
                orderRejectedEvent.setOrderedItem(eventOrder.getOrderedItem());
                orderRejectedEvent.setCustomerEmail(eventOrder.getCustomerEmail());
                orderRejectedEvent.setReason("We don´t have your ordered item in our inventory");

                rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_OUTPUT, RabbitConfig.ORDER_REJECTED_ROOTING_KEY, orderRejectedEvent);

            } else {


                OrderConfirmedEvent orderConfirmedEvent = new OrderConfirmedEvent();
                orderConfirmedEvent.setCustomerEmail(eventOrder.getCustomerEmail());
                orderConfirmedEvent.setOrderedItem(eventOrder.getOrderedItem());
                orderConfirmedEvent.setOrderedQuantity(eventOrder.getOrderedQuantity());
                orderConfirmedEvent.setPrice(inventoryItem.getPrice());

                inventoryItem.setQuantity(quantity - eventOrder.getOrderedQuantity());
                inventoryRepository.save(inventoryItem);

                rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_OUTPUT, RabbitConfig.ORDER_CONFIRMED_ROOTING_KEY, orderConfirmedEvent);
            }

        }
    }
}