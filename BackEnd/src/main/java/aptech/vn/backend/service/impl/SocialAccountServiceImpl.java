package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.SocialAccountDTO;
import aptech.vn.backend.entity.SocialAccount;
import aptech.vn.backend.entity.SocialProvider;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.mapper.SocialAccountMapper;
import aptech.vn.backend.repository.SocialAccountRepository;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.service.SocialAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SocialAccountServiceImpl implements SocialAccountService {
    private final SocialAccountMapper socialAccountMapper;
    private final SocialAccountRepository socialAccountRepository;
    private final UserRepository userRepository;

    public SocialAccountServiceImpl(
            SocialAccountRepository socialAccountRepository,
            UserRepository userRepository,
            SocialAccountMapper socialAccountMapper) {
        this.socialAccountRepository = socialAccountRepository;
        this.userRepository = userRepository;
        this.socialAccountMapper = socialAccountMapper;
    }

    @Override
    public List<SocialAccountDTO.GetSocialAccountDto> findAll() {
        return socialAccountRepository.findAll()
                .stream()
                .map(socialAccountMapper::toGetSocialAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SocialAccountDTO.GetSocialAccountDto> findById(Long id) {
        return socialAccountRepository.findById(id)
                .map(socialAccountMapper::toGetSocialAccountDto);
    }

    @Override
    @Transactional
    public SocialAccountDTO.GetSocialAccountDto saveOrUpdate(SocialAccountDTO.SaveSocialAccountDto socialAccountDTO) {
        SocialAccount socialAccount;

        if (socialAccountDTO.getId() == null || socialAccountDTO.getId() == 0) {
            // INSERT case
            socialAccount = new SocialAccount();
            socialAccount.setCreatedAt(LocalDateTime.now());
            socialAccount.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<SocialAccount> existingAccount = socialAccountRepository.findById(socialAccountDTO.getId());
            if (existingAccount.isEmpty()) {
                throw new RuntimeException("Social account not found with ID: " + socialAccountDTO.getId());
            }
            socialAccount = existingAccount.get();
            socialAccount.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý user relationship
        User user = userRepository.findById(socialAccountDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + socialAccountDTO.getUserId()));
        socialAccount.setUser(user);

        // Cập nhật các trường khác
        socialAccount.setProvider(socialAccountDTO.getProvider());
        socialAccount.setProviderId(socialAccountDTO.getProviderId());
        socialAccount.setProviderEmail(socialAccountDTO.getProviderEmail());
        socialAccount.setName(socialAccountDTO.getName());
        socialAccount.setAvatarUrl(socialAccountDTO.getAvatarUrl());
        socialAccount.setAccessToken(socialAccountDTO.getAccessToken());
        socialAccount.setRefreshToken(socialAccountDTO.getRefreshToken());
        socialAccount.setTokenExpiresAt(socialAccountDTO.getTokenExpiresAt());

        SocialAccount savedAccount = socialAccountRepository.save(socialAccount);
        return socialAccountMapper.toGetSocialAccountDto(savedAccount);
    }

    @Override
    public void deleteById(Long id) {
        socialAccountRepository.deleteById(id);
    }

    @Override
    public List<SocialAccountDTO.GetSocialAccountDto> findByUserId(Long userId) {
        return socialAccountRepository.findByUser_Id(userId)
                .stream()
                .map(socialAccountMapper::toGetSocialAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SocialAccountDTO.GetSocialAccountDto> findByProviderAndProviderId(SocialProvider provider, String providerId) {
        return socialAccountRepository.findByProviderAndProviderId(provider, providerId)
                .map(socialAccountMapper::toGetSocialAccountDto);
    }

    @Override
    public List<SocialAccountDTO.GetSocialAccountDto> findByProvider(SocialProvider provider) {
        return socialAccountRepository.findByProvider(provider)
                .stream()
                .map(socialAccountMapper::toGetSocialAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SocialAccountDTO.GetSocialAccountDto> findByUserIdAndProvider(Long userId, SocialProvider provider) {
        return socialAccountRepository.findByUser_IdAndProvider(userId, provider)
                .map(socialAccountMapper::toGetSocialAccountDto);
    }

    @Override
    public Optional<SocialAccountDTO.GetSocialAccountDto> findByProviderEmail(String email) {
        return socialAccountRepository.findByProviderEmail(email)
                .map(socialAccountMapper::toGetSocialAccountDto);
    }
}