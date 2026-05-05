package org.boris.inventory.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_INPUT = "order.exchange";
    public static final String EXCHANGE_OUTPUT = "order.events.exchange";
    public static final String ORDER_CREATED_INVENTORY_QUEUE = "order.created.inventory";
    public static final String ORDER_CONFIRMED_ROOTING_KEY = "order.confirmed";
    public static final String ORDER_REJECTED_ROOTING_KEY = "order.rejected";


    @Bean
    public Queue orderCreatedInventoryQueue() {
        return new Queue(ORDER_CREATED_INVENTORY_QUEUE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_INPUT);
    }

    @Bean
    public Binding orderCreatedInventoryBinding(Queue orderCreatedInventoryQueue, DirectExchange directExchange) {
        return BindingBuilder
                .bind(orderCreatedInventoryQueue)
                .to(directExchange)
                .with("order.created");
    }


    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter);
        return factory;
    }
}