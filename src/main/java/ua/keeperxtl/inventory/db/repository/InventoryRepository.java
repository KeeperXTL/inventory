package ua.keeperxtl.inventory.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.keeperxtl.inventory.db.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findByInventoryId(Integer id);
}
