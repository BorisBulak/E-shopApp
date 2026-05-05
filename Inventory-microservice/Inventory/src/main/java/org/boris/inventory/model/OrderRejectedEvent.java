package org.boris.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRejectedEvent {
    private String customerEmail;
    private String orderedItem;
    private String reason;
}
