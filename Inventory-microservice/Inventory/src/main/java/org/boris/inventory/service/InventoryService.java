package org.boris.inventory.service;

import org.boris.inventory.model.InventoryItem;
import org.boris.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public String addToInventory(InventoryItem inventoryItem) {
        inventoryRepository.save(inventoryItem);
        return "Inventory added successfully";
    }
}
