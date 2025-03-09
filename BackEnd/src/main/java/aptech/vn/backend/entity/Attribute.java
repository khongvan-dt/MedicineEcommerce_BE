package aptech.vn.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
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
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(name = "price_in", nullable = false)
    private BigDecimal priceIn;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(name = "price_out", nullable = false)
    private BigDecimal priceOut;

    @NotNull
    @Min(0)
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Future
    @Column(name = "expiry_date", nullable = true)
    private LocalDate expiryDate;
}
