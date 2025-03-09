package aptech.vn.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "medicine_batches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class MedicineBatch extends BaseEntity {
    @NotBlank
    @Column(name = "batch_name", nullable = false, unique = true)
    private String batchName;

    @NotBlank
    @Column(name = "medicine_code", nullable = false)
    private String medicineCode;

    @NotNull
    @Min(0)
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Future
    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;
}
