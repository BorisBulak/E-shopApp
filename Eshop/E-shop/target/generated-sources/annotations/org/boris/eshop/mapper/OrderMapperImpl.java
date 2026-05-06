package org.boris.eshop.mapper;

import javax.annotation.processing.Generated;
import org.boris.eshop.model.orderrabbitmq.EventOrder;
import org.boris.eshop.model.repoorder.Order;
import org.boris.eshop.model.request.CustomerOrder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-06T12:44:31+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (BellSoft)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public CustomerOrder toCustomerOrder(Order order) {
        if ( order == null ) {
            return null;
        }

        CustomerOrder customerOrder = new CustomerOrder();

        customerOrder.setCustomerEmail( order.getCustomerEmail() );
        customerOrder.setOrderedItem( order.getOrderedItem() );
        customerOrder.setOrderedQuantity( order.getOrderedQuantity() );

        return customerOrder;
    }

    @Override
    public Order toOrder(CustomerOrder customerOrder) {
        if ( customerOrder == null ) {
            return null;
        }

        Order order = new Order();

        order.setCustomerEmail( customerOrder.getCustomerEmail() );
        order.setOrderedItem( customerOrder.getOrderedItem() );
        order.setOrderedQuantity( customerOrder.getOrderedQuantity() );

        return order;
    }

    @Override
    public EventOrder toEventOrder(CustomerOrder customerOrder) {
        if ( customerOrder == null ) {
            return null;
        }

        EventOrder eventOrder = new EventOrder();

        eventOrder.setCustomerEmail( customerOrder.getCustomerEmail() );
        eventOrder.setOrderedItem( customerOrder.getOrderedItem() );
        eventOrder.setOrderedQuantity( customerOrder.getOrderedQuantity() );

        return eventOrder;
    }

    @Override
    public EventOrder toEventOrder(Order order) {
        if ( order == null ) {
            return null;
        }

        EventOrder eventOrder = new EventOrder();

        eventOrder.setCustomerEmail( order.getCustomerEmail() );
        eventOrder.setOrderedItem( order.getOrderedItem() );
        eventOrder.setOrderedQuantity( order.getOrderedQuantity() );

        return eventOrder;
    }
}
