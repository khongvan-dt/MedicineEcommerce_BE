package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MedicineBatchDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetDto {
        private Long id;
        private String batchName;
        private String medicineCode;
        private Integer quantity;
        private LocalDate expiryDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDto {
        private Long id;
        private String batchName;
        private String medicineCode;
        private Integer quantity;
        private LocalDate expiryDate;
    }
}