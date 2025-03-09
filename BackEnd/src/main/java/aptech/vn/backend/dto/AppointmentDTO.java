package aptech.vn.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    @Schema(description = "ID cuộc hẹn", example = "1")
    private Long id;

    @Schema(description = "ID bệnh nhân", example = "1001")
    private Long patientId;

    @Schema(description = "ID bác sĩ", example = "2002")
    private Long doctorId;

    @Schema(description = "Ngày và giờ đặt hẹn", example = "2025-03-10T14:30:00")
    private LocalDateTime appointmentDate;

    @Schema(description = "Thời gian cuộc hẹn", example = "14:30:00")
    private LocalTime appointmentTime;

    @Schema(description = "Thời gian tạo", example = "2025-03-01T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Thời gian cập nhật", example = "2025-03-05T12:00:00")
    private LocalDateTime updatedAt;
}
