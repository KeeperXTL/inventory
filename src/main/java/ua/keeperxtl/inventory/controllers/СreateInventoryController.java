package ua.keeperxtl.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.keeperxtl.inventory.db.models.Inventory;
import ua.keeperxtl.inventory.db.models.Users;
import ua.keeperxtl.inventory.db.repository.InventoryRepository;
import ua.keeperxtl.inventory.db.repository.UsersRepository;

import java.sql.Date;
import java.sql.Timestamp;

@Controller
public class СreateInventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private UsersRepository usersRepository;
    @GetMapping("/createInventory")
    public void createInventory(Model model) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Inventory inventory = new Inventory(date, usersRepository.findByUsername(auth.getName()));
        inventoryRepository.save(inventory);
    }
    @GetMapping("/deleteInventory/{id}")
    public String deleteInventory(@PathVariable(value = "id") int id) {
        Inventory inventory = inventoryRepository.findByInventoryId(id);
        inventoryRepository.delete(inventory);
        return "200";
    }
}
