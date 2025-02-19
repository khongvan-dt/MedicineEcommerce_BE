package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class OrderDetail extends BaseEntity {
    @ManyToOne
    private Order order;
    @ManyToOne
    private Medicine medicine;
    private Integer quantity;
    private BigDecimal unitPrice;
}
