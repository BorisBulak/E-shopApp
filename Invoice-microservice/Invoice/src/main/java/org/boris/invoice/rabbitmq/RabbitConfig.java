package org.boris.invoice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_INPUT = "order.events.exchange";
    public static final String INVOICE_QUEUE = "order.events.invoice.queue";
    public static final String ORDER_CONFIRMED_ROUTING_KEY = "order.confirmed";

    @Bean
    public Queue orderEventsQueue() {
        return new Queue(INVOICE_QUEUE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_INPUT);
    }

    @Bean
    public Binding orderEventsBinding(Queue orderEventsQueue,DirectExchange directExchange) {
        return BindingBuilder
                .bind(orderEventsQueue)
                .to(directExchange)
                .with(ORDER_CONFIRMED_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter  jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter);
        return factory;
    }


}
