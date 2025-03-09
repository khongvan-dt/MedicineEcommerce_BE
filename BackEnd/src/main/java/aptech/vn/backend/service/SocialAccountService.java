package aptech.vn.backend.service;

import aptech.vn.backend.dto.SocialAccountDTO;
import aptech.vn.backend.entity.SocialProvider;

import java.util.List;
import java.util.Optional;

public interface SocialAccountService {
    List<SocialAccountDTO> findAll();
    Optional<SocialAccountDTO> findById(Long id);
    SocialAccountDTO save(SocialAccountDTO socialAccountDTO);
    void deleteById(Long id);
    List<SocialAccountDTO> findByUserId(Long userId);
    Optional<SocialAccountDTO> findByProviderAndProviderId(SocialProvider provider, String providerId);
    List<SocialAccountDTO> findByProvider(SocialProvider provider);
    Optional<SocialAccountDTO> findByUserIdAndProvider(Long userId, SocialProvider provider);
    Optional<SocialAccountDTO> findByProviderEmail(String email);
}