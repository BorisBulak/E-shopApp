package org.boris.eshop.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {

    @NotNull
    @NotBlank(message = "Email is required")
    private String customerEmail;
    @NotNull
    @NotBlank(message = "Ordered item is required")
    private String orderedItem;
    @Min(1)
    @Max(50)
    private Long orderedQuantity;


}
