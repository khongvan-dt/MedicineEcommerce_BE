package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private Long medicineId;
    private String dosage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
