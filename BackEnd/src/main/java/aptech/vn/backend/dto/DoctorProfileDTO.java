package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DoctorProfileDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetDoctorProfileDto {
        private Long id;
        private Long userId;
        private String experience;
        private String specialization;
        private String workplace;
        private BigDecimal accountBalance;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDoctorProfileDto {
        private Long id;
        private Long userId;
        private String experience;
        private String specialization;
        private String workplace;
        private BigDecimal accountBalance;
    }
}