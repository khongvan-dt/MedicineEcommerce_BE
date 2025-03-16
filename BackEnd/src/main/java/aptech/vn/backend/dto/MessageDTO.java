package aptech.vn.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class MessageDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetMessageDto {
        private Long id;
        private Long senderId;
        private Long receiverId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveMessageDto {
         private Long id;
         private Long senderId;
         private Long receiverId;
         private String content;
    }
}