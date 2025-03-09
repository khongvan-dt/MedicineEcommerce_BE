package aptech.vn.backend.dto;

import aptech.vn.backend.entity.BookingStatus;
import aptech.vn.backend.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBookingDTO {
    private Long id;
    private Long serviceId;
    private Long patientId;
    private BigDecimal totalPrice;
    private PaymentMethod paymentMethod;
    private BookingStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}