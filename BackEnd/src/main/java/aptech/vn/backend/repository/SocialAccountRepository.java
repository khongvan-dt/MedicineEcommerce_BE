package aptech.vn.backend.repository;

import aptech.vn.backend.entity.SocialAccount;
import aptech.vn.backend.entity.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocialAccountRepository extends JpaRepository<SocialAccount, Long> {
    List<SocialAccount> findByUserId(Long userId);
    List<SocialAccount> findByProvider(SocialProvider provider);
    Optional<SocialAccount> findByProviderAndProviderId(SocialProvider provider, String providerId);
    Optional<SocialAccount> findByProviderAndProviderEmail(SocialProvider provider, String providerEmail);
    Optional<SocialAccount> findByUserIdAndProvider(Long userId, SocialProvider provider);
}