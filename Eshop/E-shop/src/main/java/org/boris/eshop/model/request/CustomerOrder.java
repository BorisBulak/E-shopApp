package org.boris.eshop.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {

    @NotNull
    private String customerEmail;
    @NotNull
    private String orderedItem;
    @Min(1)
    @Max(50)
    private Long orderedQuantity;


}
