package ua.keeperxtl.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.keeperxtl.inventory.db.models.Inventory;
import ua.keeperxtl.inventory.db.repository.InventoryRepository;

import java.util.List;

@Controller("/")
public class IndexController {
    @Autowired
    InventoryRepository inventoryRepository;
    @GetMapping
    public String inventory(Model model) {
        List<Inventory> inventories = inventoryRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
        model.addAttribute("inventories", inventories);
        return "index";
    }
}
