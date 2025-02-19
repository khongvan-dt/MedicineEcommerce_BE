package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vouchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Voucher extends BaseEntity {
    private String code;
    @ManyToOne
    private Medicine medicine;
    private Double voucherPercentage;
    private Integer stock;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
