package NorthPoint.Assignment.controller;

import NorthPoint.Assignment.model.Item;
import NorthPoint.Assignment.repository.itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {

    @Autowired
    private itemRepository itemRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/inventory")
    public String viewInventory(Model model, 
                                @RequestParam(required = false) String search,
                                @RequestParam(defaultValue = "name") String sortBy,
                                @RequestParam(defaultValue = "0") int page) {
        
        Pageable pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        Page<Item> itemPage;
        
        if (search != null && !search.isEmpty()) {
            itemPage = itemRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            itemPage = itemRepository.findAll(pageable);
        }
        
        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", itemPage.getTotalPages());
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        
        return "inventory"; 
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("item", new Item()); 
        return "add-item"; 
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute("item") Item item) {
        itemRepository.save(item); 
        return "redirect:/inventory"; 
    }
}