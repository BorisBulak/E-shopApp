package org.boris.inventory.repository;

import org.boris.inventory.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Integer> {

    public InventoryItem findByItemName(String itemName);
}
