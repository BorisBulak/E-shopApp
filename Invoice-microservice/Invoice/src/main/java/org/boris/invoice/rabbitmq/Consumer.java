package org.boris.invoice.rabbitmq;

import org.boris.invoice.model.OrderConfirmedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @RabbitListener(queues = RabbitConfig.INVOICE_QUEUE)
    public void received(OrderConfirmedEvent eventOrder) {
        double total = eventOrder.getOrderedQuantity()*eventOrder.getPrice();
        System.out.println("***INVOICE***");
        System.out.println("Item -> " + eventOrder.getOrderedItem());
        System.out.println("Quantity -> " + eventOrder.getOrderedQuantity());
        System.out.println("Customer Email -> " + eventOrder.getCustomerEmail());
        System.out.println("Price -> " + total);
        System.out.println("Signature -> ESHOPTEST");
    }
}
