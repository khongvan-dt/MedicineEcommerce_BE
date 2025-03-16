package aptech.vn.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vouchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Vouchers extends BaseEntity {
    @NotBlank
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @NotNull
    @Min(0)
    @Column(name = "voucher_percentage", nullable = false)
    private Double voucherPercentage;

    @NotNull
    @Min(0)
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Future
    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;
}
