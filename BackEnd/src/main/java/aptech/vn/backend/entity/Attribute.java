package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "attributes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Attribute extends BaseEntity {
    private String name;
    private BigDecimal priceIn;
    private BigDecimal priceOut;
    private Integer stock;
    private LocalDate expiryDate;
}
