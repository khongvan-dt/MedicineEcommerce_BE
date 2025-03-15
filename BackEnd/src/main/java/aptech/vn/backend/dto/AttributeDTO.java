package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AttributeDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetDto {
        private Long id;
        private String name;
        private BigDecimal priceIn;
        private BigDecimal priceOut;
        private Integer stock;
        private LocalDate expiryDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDto {
        private Long id;
        private String name;
        private BigDecimal priceIn;
        private BigDecimal priceOut;
        private Integer stock;
        private LocalDate expiryDate;

    }

}