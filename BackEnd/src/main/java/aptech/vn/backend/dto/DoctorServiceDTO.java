package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DoctorServiceDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetDoctorServiceDto {
        private Long id;
        private Long serviceId;
        private Long doctorId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDoctorServiceDto {
        private Long id;
        private Long serviceId;
        private Long doctorId;

    }
}
