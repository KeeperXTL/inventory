package ua.keeperxtl.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.keeperxtl.inventory.db.models.Rack;
import ua.keeperxtl.inventory.db.models.Shelf;
import ua.keeperxtl.inventory.db.repository.RackRepository;
import ua.keeperxtl.inventory.db.repository.ShelfRepository;
import ua.keeperxtl.inventory.db.repository.UsersRepository;

import java.util.List;

@Controller
public class RackController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RackRepository rackRepository;
    @Autowired
    private ShelfRepository shelfRepository;
    @GetMapping("/rack/{id}")
    public String rackCon(@PathVariable(value = "id") int id, Model model) {
        List<Shelf> shelves = rackRepository.findByRackId(id).getShelfList();
        model.addAttribute("shelves", shelves);
        model.addAttribute("inventoryId", rackRepository.findByRackId(id).getInventory().getInventoryId());
        model.addAttribute("rackId", id);
        return "rack";
    }
    @GetMapping("/createShelf")
    public void createShelf(@RequestParam("rackId") int rackId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Shelf> shelves = shelfRepository.findAllByRack(rackRepository.findByRackId(rackId));
        if (shelves.isEmpty()) {
            Shelf shelf = new Shelf(rackRepository.findByRackId(rackId), 1, usersRepository.findByUsername(auth.getName()));
            shelfRepository.save(shelf);
        } else {
            int maxOrdinalNumber = 0;
            for (Shelf shelf : shelves) {
                if (shelf.getOrdinalNumber() > maxOrdinalNumber)
                    maxOrdinalNumber = shelf.getOrdinalNumber();
            }
            Shelf shelf = new Shelf(rackRepository.findByRackId(rackId), maxOrdinalNumber + 1, usersRepository.findByUsername(auth.getName()));
            shelfRepository.save(shelf);
        }
    }
    @GetMapping("/deleteShelf/{id}")
    public String deleteShelf(@PathVariable(value = "id") int id) {
        Shelf shelf = shelfRepository.findByShelfId(id);
        shelfRepository.delete(shelf);
        return "200";
    }
}
