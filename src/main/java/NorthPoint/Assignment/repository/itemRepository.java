package NorthPoint.Assignment.repository;

import NorthPoint.Assignment.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface itemRepository extends JpaRepository<Item, Long> {
    
    Page<Item> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    List<Item> findByBrandId(Long brandId);

}