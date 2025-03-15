package aptech.vn.backend.dto;

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
    public static class GetDto {
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
    public static class SaveDto {
        private Long id;
        private Long userId;
        private String bloodType;
        private String medicalHistory;
        private String allergies;
        private BigDecimal accountBalance;
    }
}