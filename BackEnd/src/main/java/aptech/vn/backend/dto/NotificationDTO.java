package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetNotificationDto {
        private Long id;
        private Long userId;
        private String message;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveNotificationDto {
        private Long id;
        private Long userId;
        private String message;
    }
}