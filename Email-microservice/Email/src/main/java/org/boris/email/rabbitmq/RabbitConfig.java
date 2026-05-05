package org.boris.email.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_INPUT = "order.events.exchange";
    public static final String EMAIL_CONFIRMED_QUEUE = "order.events.confirmed.queue";
    public static final String EMAIL_REJECTED_QUEUE = "order.events.rejected.queue";
    public static final String ORDER_CONFIRMED_ROOTING_KEY = "order.confirmed";
    public static final String ORDER_REJECTED_ROOTING_KEY = "order.rejected";

    @Bean
    public Queue confirmQueue() {
        return new Queue(EMAIL_CONFIRMED_QUEUE);
    }

    @Bean
    public Queue rejectedQueue() {
        return new Queue(EMAIL_REJECTED_QUEUE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_INPUT);
    }

    @Bean
    public Binding confirmBinding(Queue confirmQueue, DirectExchange directExchange) {
        return BindingBuilder
                .bind(confirmQueue)
                .to(directExchange)
                .with(ORDER_CONFIRMED_ROOTING_KEY);
    }

    @Bean
    public Binding rejectedBinding(Queue rejectedQueue, DirectExchange directExchange) {
        return  BindingBuilder
                .bind(rejectedQueue)
                .to(directExchange)
                .with(ORDER_REJECTED_ROOTING_KEY);

    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter);
        return factory;
    }
}