package ua.keeperxtl.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.keeperxtl.inventory.db.models.Inventory;
import ua.keeperxtl.inventory.db.models.Rack;
import ua.keeperxtl.inventory.db.repository.InventoryRepository;
import ua.keeperxtl.inventory.db.repository.RackRepository;
import ua.keeperxtl.inventory.db.repository.UsersRepository;

import java.util.List;

@Controller
public class InventoryController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private RackRepository rackRepository;
    @GetMapping("/inventory/{id}")
    public String inventoryCon(@PathVariable(value = "id") int id, Model model) {
        List<Rack> racks = inventoryRepository.findByInventoryId(id).getRackList();
        model.addAttribute("racks", racks);
        model.addAttribute("inventoryId", id);
        return "inventory";
    }
    @GetMapping("/createRack")
    public void createRack(@RequestParam("inventoryId") int inventoryId, @RequestParam("name") String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Rack rack = new Rack(usersRepository.findByUsername(auth.getName()), name, inventoryRepository.findByInventoryId(inventoryId));
        rackRepository.save(rack);
    }
    @GetMapping("/deleteRack/{id}")
    public String deleteRack(@PathVariable(value = "id") int id) {
        Rack rack = rackRepository.findByRackId(id);
        rackRepository.delete(rack);
        return "200";
    }
}
