package aptech.vn.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PatientProfileDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetPatientProfileDto {
        private Long id;
        private Long userId;
        private String bloodType;
        private String medicalHistory;
        private String allergies;
        private BigDecimal accountBalance;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SavePatientProfileDto {
        @Schema(description = "id")
        private Long id;
        private Long userId;
        private String bloodType;
        private String medicalHistory;
        private String allergies;
        private BigDecimal accountBalance;
    }
}