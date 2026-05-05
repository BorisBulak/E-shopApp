package org.boris.inventory.controller;

import org.boris.inventory.model.InventoryItem;
import org.boris.inventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add")
public class InventoryController {


    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody InventoryItem inventoryItem) {
        String result = inventoryService.addToInventory(inventoryItem);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
