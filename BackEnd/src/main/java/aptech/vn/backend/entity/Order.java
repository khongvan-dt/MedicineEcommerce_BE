package aptech.vn.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {
    @NotBlank
    @Column(name = "order_code", nullable = false, unique = true)
    private String orderCode;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientProfile patient;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "voucher_code", nullable = true)
    private String voucherCode;

    @Column(name = "discount_amount", nullable = true)
    private BigDecimal discountAmount;

    @Column(name = "note", nullable = true, columnDefinition = "TEXT")
    private String note;
}