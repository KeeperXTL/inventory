package ua.keeperxtl.inventory.db.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.keeperxtl.inventory.db.models.Item;
import ua.keeperxtl.inventory.db.models.Shelf;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findAllByShelf(Shelf shelf, Sort sort);
    Item findByItemId(int id);
}
