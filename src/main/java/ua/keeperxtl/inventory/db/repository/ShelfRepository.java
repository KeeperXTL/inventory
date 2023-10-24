package ua.keeperxtl.inventory.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.keeperxtl.inventory.db.models.Rack;
import ua.keeperxtl.inventory.db.models.Shelf;

import java.util.List;

public interface ShelfRepository extends JpaRepository<Shelf, Integer> {
    List<Shelf> findAllByRack(Rack rack);
    Shelf findByShelfId(int id);
}
