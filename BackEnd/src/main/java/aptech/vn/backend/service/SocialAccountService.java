package aptech.vn.backend.service;

import aptech.vn.backend.dto.SocialAccountDTO;
import aptech.vn.backend.entity.SocialProvider;

import java.util.List;
import java.util.Optional;

public interface SocialAccountService {
    List<SocialAccountDTO.GetDto> findAll();
    Optional<SocialAccountDTO.GetDto> findById(Long id);
    SocialAccountDTO.GetDto saveOrUpdate(SocialAccountDTO.SaveDto socialAccountDTO);
    void deleteById(Long id);
    List<SocialAccountDTO.GetDto> findByUserId(Long userId);
    Optional<SocialAccountDTO.GetDto> findByProviderAndProviderId(SocialProvider provider, String providerId);
    List<SocialAccountDTO.GetDto> findByProvider(SocialProvider provider);
    Optional<SocialAccountDTO.GetDto> findByUserIdAndProvider(Long userId, SocialProvider provider);
    Optional<SocialAccountDTO.GetDto> findByProviderEmail(String email);
}