package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDetailDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetOrderDetailDto {
        private Long id;
        private Long orderId;
        private Long medicineId;
        private Integer quantity;
        private BigDecimal unitPrice;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveOrderDetailDto {
        private Long id;
        private Long orderId;
        private Long medicineId;
        private Integer quantity;
        private BigDecimal unitPrice;
    }
}