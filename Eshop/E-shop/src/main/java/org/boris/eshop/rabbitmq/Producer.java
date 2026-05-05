package org.boris.eshop.rabbitmq;

import org.boris.eshop.model.orderrabbitmq.EventOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(EventOrder eventOrder) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_OUTPUT,RabbitConfig.INVENTORY_ROOTING_KEY, eventOrder);
    }

}
