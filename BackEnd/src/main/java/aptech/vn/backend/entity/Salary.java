package aptech.vn.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "salaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Salary extends BaseEntity {
    @ManyToOne
    private User user;
    private String bankCode;
    private String bankName;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
