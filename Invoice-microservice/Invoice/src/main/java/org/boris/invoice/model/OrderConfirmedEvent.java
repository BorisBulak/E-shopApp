package org.boris.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmedEvent {
    private String customerEmail;
    private String orderedItem;
    private Long orderedQuantity;
    private int price;

}
