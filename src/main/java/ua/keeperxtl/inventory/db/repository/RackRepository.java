package ua.keeperxtl.inventory.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.keeperxtl.inventory.db.models.Inventory;
import ua.keeperxtl.inventory.db.models.Rack;

import java.util.List;

public interface RackRepository extends JpaRepository<Rack, Integer> {
    List<Rack> findAllByInventory(Inventory inventory);
    Rack findByRackId(int rackId);
}
