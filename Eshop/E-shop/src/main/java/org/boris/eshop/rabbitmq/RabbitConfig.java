package org.boris.eshop.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String INVENTORY_ROOTING_KEY = "order.created";
    public static final String EXCHANGE_OUTPUT = "order.exchange";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_OUTPUT);
    }


    @Bean
    public Jackson2JsonMessageConverter  jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate  rabbitTemplate(ConnectionFactory connectionFactory,Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }



    //    public static final String ORDER_CREATED_EMAIL_QUEUE = "order.created.email";
//    public static final String ORDER_CREATED_BILLING_QUEUE = "order.created.billing";
//    public static final String ORDER_CREATED_INVENTORY_QUEUE = "order.created.inventory";



    //    @Bean
//    public Queue orderCreatedEmailQueue() {
//        return new Queue(ORDER_CREATED_EMAIL_QUEUE,true);
//    }
//
//    @Bean
//    public Queue orderCreatedBillingQueue() {
//        return new Queue(ORDER_CREATED_BILLING_QUEUE,true);
//    }
//
//    @Bean
//    public Queue orderCreatedInventoryQueue() {
//        return new Queue(ORDER_CREATED_INVENTORY_QUEUE,true);
//    }
//
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange(EXCHANGE_NAME);
//    }
//
//
//    @Bean
//    public Binding  orderCreatedEmailBinding(Queue orderCreatedEmailQueue, FanoutExchange fanoutExchange) {
//        return BindingBuilder
//                .bind(orderCreatedEmailQueue)
//                .to(fanoutExchange);
//    }
//
//    @Bean
//    public Binding orderCreatedBillingBinding(Queue orderCreatedBillingQueue,FanoutExchange fanoutExchange) {
//        return BindingBuilder
//                .bind(orderCreatedBillingQueue)
//                .to(fanoutExchange);
//    }
//
//    @Bean
//    public DirectExchange  directExchange() {
//        return new DirectExchange(EXCHANGE_NAME);
//    }
//
//    @Bean
//    public Binding orderCreatedInventoryBinding(Queue orderCreatedInventoryQueue, DirectExchange directExchange) {
//        return BindingBuilder
//                .bind(orderCreatedInventoryQueue)
//                .to(directExchange)
//                .with(RabbitConfig.INVENTORY_ROOTING_KEY);
//    }

}
