package aptech.vn.backend.dto;

import aptech.vn.backend.entity.SocialProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialAccountDTO {
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
