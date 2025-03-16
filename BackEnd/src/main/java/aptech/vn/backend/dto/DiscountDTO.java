package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class DiscountDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetDiscountDto {
        private Long id;
        private String code;
        private String name;
        private Long medicineId;
        private Double discountPercentage;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDiscountDto {
        private Long id;
        private String code;
        private String name;
        private Long medicineId;
        private Double discountPercentage;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
    }
}