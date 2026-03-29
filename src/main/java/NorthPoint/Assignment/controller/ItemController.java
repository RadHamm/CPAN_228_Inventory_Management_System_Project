package NorthPoint.Assignment.controller;

import NorthPoint.Assignment.model.Item;
import NorthPoint.Assignment.repository.BrandRepository;
import NorthPoint.Assignment.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/inventory")
    public String viewInventory(Model model, 
                                @RequestParam(required = false) String search,
                                @RequestParam(required = false) Long brandId,
                                @RequestParam(defaultValue = "name") String sortBy,
                                @RequestParam(defaultValue = "0") int page) {
        
        Pageable pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        Page<Item> itemPage;
        
        if (brandId != null) {
            itemPage = itemRepository.findByBrandId(brandId, pageable);
        } else if
            (search != null && !search.isEmpty()) {
            itemPage = itemRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            itemPage = itemRepository.findAll(pageable);
        }
        
        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", itemPage.getTotalPages());
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("brandId", brandId);
        model.addAttribute("brands", brandRepository.findAll());
        
        return "inventory"; 
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("item", new Item()); 
        model.addAttribute("brands", brandRepository.findAll());
        return "add-item"; 
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") Item item, BindingResult result) {

        if (result.hasErrors()) {
            return "add-item"; 
        }

        itemRepository.save(item); 
        return "redirect:/inventory";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "admin"; 
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id, RedirectAttributes redirectAttribute) {
        try {
        itemRepository.deleteById(id);
        } catch (Exception e) {
            redirectAttribute.addFlashAttribute("error", "Unable to delete item.");
        }
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id)));
        model.addAttribute("brands", brandRepository.findAll());
        return "add-item";
    }

    @PostMapping("/edit/{id}")
    public String updateItem(@PathVariable Long id, @Validated @ModelAttribute("item") Item item, BindingResult result, RedirectAttributes redirectAttribute) {
        if (result.hasErrors()) {
            return "add-item";
        }

        Item existingItem = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        existingItem.setName(item.getName());
        existingItem.setSku(item.getSku());
        existingItem.setPrice(item.getPrice());
        existingItem.setBrand(item.getBrand());

        itemRepository.save(existingItem);
        redirectAttribute.addFlashAttribute("success", "Item updated successfully.");
        return "redirect:/admin";
    }
}