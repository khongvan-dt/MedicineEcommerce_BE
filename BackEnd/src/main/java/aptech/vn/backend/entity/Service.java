package aptech.vn.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Service extends BaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;
}
