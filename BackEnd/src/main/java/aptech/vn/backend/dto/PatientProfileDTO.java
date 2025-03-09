package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfileDTO {
    private Long id;
    private Long userId;
    private String bloodType;
    private String medicalHistory;
    private String allergies;
    private BigDecimal accountBalance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}