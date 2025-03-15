package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetDto {
        private Long id;
        private String fullName;
        private String phone;
        private String address;
        private String email;
        private LocalDateTime lastLogin;
        private Integer countLock;
        private Boolean enabled;
        private Boolean locked;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDto {
        private Long id;
        private String fullName;
        private String phone;
        private String address;
        private String email;
        private String password;
        private Boolean enabled;
        private Boolean locked;
        private Integer countLock;
    }
}