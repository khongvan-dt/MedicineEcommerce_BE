package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "discounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Discount extends BaseEntity {
    private String code;
    private String name;
    @ManyToOne
    private Medicine medicine;
    private Double discountPercentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
