package org.boris.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventOrder {
    private String customerEmail;
    private String orderedItem;
    private Long orderedQuantity;
}
