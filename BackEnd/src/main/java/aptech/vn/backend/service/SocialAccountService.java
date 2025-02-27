package aptech.vn.backend.service;

import aptech.vn.backend.entity.SocialAccount;
import aptech.vn.backend.entity.SocialProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SocialAccountService {
    SocialAccount save(SocialAccount socialAccount);
    Optional<SocialAccount> findById(Long id);
    List<SocialAccount> findAll();
    Page<SocialAccount> findAll(Pageable pageable);
    void deleteById(Long id);
    List<SocialAccount> findByUserId(Long userId);
    Optional<SocialAccount> findByProviderAndProviderId(SocialProvider provider, String providerId);
    List<SocialAccount> findByProvider(SocialProvider provider);
    boolean linkAccountToUser(Long userId, SocialAccount socialAccount);
    boolean updateToken(Long accountId, String accessToken, String refreshToken);
}