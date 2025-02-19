package aptech.vn.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialProvider {
    GOOGLE("google"),
    FACEBOOK("facebook"),
    GITHUB("github");

    private final String providerType;
}