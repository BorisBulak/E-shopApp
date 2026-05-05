package org.boris.email.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRejectedEvent {
    private String customerEmail;
    private String orderedItem;
    private String reason;
}

