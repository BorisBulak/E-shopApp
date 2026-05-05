package org.boris.email.rabbitmq;

import org.boris.email.model.OrderConfirmedEvent;
import org.boris.email.model.OrderRejectedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @RabbitListener(queues = RabbitConfig.EMAIL_CONFIRMED_QUEUE)
    public void confirmOrder(OrderConfirmedEvent orderConfirmedEvent) {
        System.out.println("EMAIL SERVICE TRIGGERED");
        System.out.println("Sending email to " + orderConfirmedEvent.getCustomerEmail());
        System.out.println("Hello, your order of " + orderConfirmedEvent.getOrderedItem() + " was received");
    }

    @RabbitListener(queues = RabbitConfig.EMAIL_REJECTED_QUEUE)
    public void rejectedOrder(OrderRejectedEvent orderRejectedEvent) {
        System.out.println("EMAIL SERVICE TRIGGERED");
        System.out.println("Sending email to " + orderRejectedEvent.getCustomerEmail());
        System.out.println("Rejected reason : " + orderRejectedEvent.getReason());
    }
}