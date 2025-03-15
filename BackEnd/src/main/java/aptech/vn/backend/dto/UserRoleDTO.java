package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class UserRoleDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetDto {
        private Long id;
        private Long userId;
        private Long roleId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDto {
        private Long id;
        private Long userId;
        private Long roleId;
    }
}