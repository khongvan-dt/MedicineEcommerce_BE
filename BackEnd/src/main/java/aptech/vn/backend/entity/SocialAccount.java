package aptech.vn.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "social_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class SocialAccount extends BaseEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private SocialProvider provider;

    @NotNull
    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Column(name = "provider_email", nullable = true)
    private String providerEmail;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "avatar_url", nullable = true)
    private String avatarUrl;

    @Column(name = "access_token", nullable = true)
    private String accessToken;

    @Column(name = "refresh_token", nullable = true)
    private String refreshToken;

    @Column(name = "token_expires_at", nullable = true)
    private LocalDateTime tokenExpiresAt;
}
