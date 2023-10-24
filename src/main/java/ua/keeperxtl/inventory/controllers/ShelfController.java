package ua.keeperxtl.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.keeperxtl.inventory.db.models.Item;
import ua.keeperxtl.inventory.db.repository.ItemRepository;
import ua.keeperxtl.inventory.db.repository.ShelfRepository;
import ua.keeperxtl.inventory.db.repository.UsersRepository;

import java.util.List;

@Controller
public class ShelfController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ShelfRepository shelfRepository;
    @GetMapping("/shelf/{id}")
    public String shelfCon(@PathVariable(value = "id") int id, Model model) {
        List<Item> items = shelfRepository.findByShelfId(id).getItemList();
        model.addAttribute("items", items);
        model.addAttribute("rackId", shelfRepository.findByShelfId(id).getRack().getRackId());
        model.addAttribute("shelfId", id);
        return "shelf";
    }
    @GetMapping("/addItem")
    public String addItem(@RequestParam("count") Float count, @RequestParam("price") Float price, @RequestParam("shelfId") int shelfId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Item item = new Item(shelfRepository.findByShelfId(shelfId), count, price, usersRepository.findByUsername(auth.getName()));
        itemRepository.save(item);
        return "redirect:/shelf/" + shelfId;
    }
    @GetMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable(value = "id") int id) {
        Item item = itemRepository.findByItemId(id);
        itemRepository.delete(item);
        return "200";
    }
    @GetMapping("/editItem")
    public String editItem(@RequestParam("count") float count, @RequestParam("price") float price, @RequestParam("itemId") int itemId) {
        Item item = itemRepository.findByItemId(itemId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        item.setCount(count);
        item.setPrice(price);
        item.setUser(usersRepository.findByUsername(auth.getName()));
        itemRepository.save(item);
        return "200";
    }
}
