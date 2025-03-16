package aptech.vn.backend.dto;

import aptech.vn.backend.entity.TrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data

public class TrackingDTO {


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetTrackingDto {
        private Long id;
        private Long orderId;
        private String location;
        private String message;
        private TrackingStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveTrackingDto {
        private Long id;
        private Long orderId;
        private String location;
        private String message;
        private TrackingStatus status;

    }
}
