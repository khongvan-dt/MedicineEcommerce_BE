package aptech.vn.backend.dto;

import aptech.vn.backend.entity.OrderStatus;
import aptech.vn.backend.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String orderCode;
    private Long patientId;
    private BigDecimal totalPrice;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private String voucherCode;
    private BigDecimal discountAmount;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
