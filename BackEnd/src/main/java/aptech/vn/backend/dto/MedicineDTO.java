package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
public class MedicineDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveMedicineDto {
        private Long id;
        private String code;
        private String name;
        private Long brandId;
        private String origin;
        private String manufacturer;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetMedicineDto {
        private Long id;
        private String code;
        private String name;
        private String origin;
        private String manufacturer;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}