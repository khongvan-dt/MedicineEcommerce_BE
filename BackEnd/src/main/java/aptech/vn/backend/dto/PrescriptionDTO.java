package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class PrescriptionDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetPrescriptionDto {
        private Long id;
        private Long doctorId;
        private Long patientId;
        private Long medicineId;
        private String dosage;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SavePrescriptionDto {
        private Long id;
        private Long doctorId;
        private Long patientId;
        private Long medicineId;
        private String dosage;
    }
}