package aptech.vn.backend.dto;

import aptech.vn.backend.entity.ConsultationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ConsultationDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetDto {
        private Long id;
        private Long patientId;
        private Long doctorId;
        private String consultationLink;
        private ConsultationStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDto {
        private Long id;
        private Long patientId;
        private Long doctorId;
        private String consultationLink;
        private ConsultationStatus status;
    }
}