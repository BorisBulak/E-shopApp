package org.boris.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventOrder {
    private String customerEmail;
    private String orderedItem;
    private Long orderedQuantity;
}
