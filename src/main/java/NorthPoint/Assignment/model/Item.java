package NorthPoint.Assignment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data 
@Entity 
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NotBlank(message = "Product name is required")
    private String name; 

    @NotBlank(message = "SKU is required")
    private String sku;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price cannot be negative") 
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand; 

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}