package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorServiceDTO {
    private Long id;
    private Long serviceId;
    private Long doctorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
