package org.boris.eshop.controller;

import jakarta.validation.Valid;
import org.boris.eshop.model.request.CustomerOrder;
import org.boris.eshop.service.EshopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class EshopController {

    private final EshopService eshopService;

    public EshopController(EshopService eshopService) {
        this.eshopService = eshopService;
    }

    @PostMapping()
    public ResponseEntity<String> createOrder(@Valid @RequestBody CustomerOrder customerOrder) {
        eshopService.createOrder(customerOrder);
        return new ResponseEntity<>("Order has been sent", HttpStatus.CREATED);
    }
}
