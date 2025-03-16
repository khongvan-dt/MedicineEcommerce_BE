package aptech.vn.backend.dto;

import aptech.vn.backend.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SalaryDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetSalaryDto {
        private Long id;
        private Long userId;
        private String bankCode;
        private String bankName;
        private BigDecimal price;
        private PaymentStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveSalaryDto {
        private Long id;
        private Long userId;
        private String bankCode;
        private String bankName;
        private BigDecimal price;
        private PaymentStatus status;
    }
}