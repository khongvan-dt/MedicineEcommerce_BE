package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ServiceDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetServiceDto {
        private Long id;
        private String name;
        private BigDecimal price;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveServiceDto {
        private Long id;
        private String name;
        private BigDecimal price;
        private String description;
    }
}