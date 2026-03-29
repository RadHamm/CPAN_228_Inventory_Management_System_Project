package NorthPoint.Assignment.service;

import org.springframework.stereotype.Service;

import NorthPoint.Assignment.model.Item;
import NorthPoint.Assignment.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void save(Item item) {
        itemRepository.save(item);
    }
}
