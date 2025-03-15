package aptech.vn.backend.dto;

import aptech.vn.backend.entity.SocialProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class SocialAccountDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetDto {
        private Long id;
        private Long userId;
        private SocialProvider provider;
        private String providerId;
        private String providerEmail;
        private String name;
        private String avatarUrl;
        private String accessToken;
        private String refreshToken;
        private LocalDateTime tokenExpiresAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDto {
        private Long id;
        private Long userId;
        private SocialProvider provider;
        private String providerId;
        private String providerEmail;
        private String name;
        private String avatarUrl;
        private String accessToken;
        private String refreshToken;
        private LocalDateTime tokenExpiresAt;
    }
}