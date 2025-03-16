package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class MedicineCategoryDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetMedicineCategoryDto {
        private Long id;
        private Long medicineId;
        private Long categoryId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveMedicineCategoryDto {
        private Long id;
        private Long medicineId;
        private Long categoryId;
    }
}