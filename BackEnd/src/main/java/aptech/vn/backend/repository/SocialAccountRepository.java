package aptech.vn.backend.repository;

import aptech.vn.backend.entity.SocialAccount;
import aptech.vn.backend.entity.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocialAccountRepository extends JpaRepository<SocialAccount, Long> {
    List<SocialAccount> findByUser_Id(Long userId);
    Optional<SocialAccount> findByProviderAndProviderId(SocialProvider provider, String providerId);
    List<SocialAccount> findByProvider(SocialProvider provider);
    Optional<SocialAccount> findByUser_IdAndProvider(Long userId, SocialProvider provider);
    Optional<SocialAccount> findByProviderEmail(String email);
}