package aptech.vn.backend.service;

import aptech.vn.backend.dto.SocialAccountDTO;
import aptech.vn.backend.entity.SocialProvider;

import java.util.List;
import java.util.Optional;

public interface SocialAccountService {
    List<SocialAccountDTO.GetSocialAccountDto> findAll();
    Optional<SocialAccountDTO.GetSocialAccountDto> findById(Long id);
    SocialAccountDTO.GetSocialAccountDto saveOrUpdate(SocialAccountDTO.SaveSocialAccountDto socialAccountDTO);
    void deleteById(Long id);
    List<SocialAccountDTO.GetSocialAccountDto> findByUserId(Long userId);
    Optional<SocialAccountDTO.GetSocialAccountDto> findByProviderAndProviderId(SocialProvider provider, String providerId);
    List<SocialAccountDTO.GetSocialAccountDto> findByProvider(SocialProvider provider);
    Optional<SocialAccountDTO.GetSocialAccountDto> findByUserIdAndProvider(Long userId, SocialProvider provider);
    Optional<SocialAccountDTO.GetSocialAccountDto> findByProviderEmail(String email);
}