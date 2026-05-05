package org.boris.eshop.mapper;

import org.boris.eshop.model.orderrabbitmq.EventOrder;
import org.boris.eshop.model.request.CustomerOrder;
import org.boris.eshop.model.repoorder.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    public CustomerOrder toCustomerOrder(Order order);

    public Order toOrder(CustomerOrder customerOrder);

    public EventOrder toEventOrder(CustomerOrder customerOrder);

    public EventOrder toEventOrder(Order order);
}
