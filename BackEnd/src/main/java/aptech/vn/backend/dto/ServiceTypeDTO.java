package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ServiceTypeDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetServiceTypeDto {
        private Long id;
        private String name;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveServiceTypeDto {
        private Long id;
        private String name;
    }
}