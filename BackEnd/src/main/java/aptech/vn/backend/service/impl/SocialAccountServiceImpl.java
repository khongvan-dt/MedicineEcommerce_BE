package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.SocialAccountDTO;
import aptech.vn.backend.entity.SocialAccount;
import aptech.vn.backend.entity.SocialProvider;
import aptech.vn.backend.mapper.SocialAccountMapper;
import aptech.vn.backend.repository.SocialAccountRepository;
import aptech.vn.backend.service.SocialAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SocialAccountServiceImpl implements SocialAccountService {
    private final SocialAccountMapper socialAccountMapper;
    private final SocialAccountRepository socialAccountRepository;

    public SocialAccountServiceImpl(SocialAccountRepository socialAccountRepository, SocialAccountMapper socialAccountMapper) {
        this.socialAccountRepository = socialAccountRepository;
        this.socialAccountMapper = socialAccountMapper;
    }

    @Override
    public List<SocialAccountDTO> findAll() {
        return socialAccountRepository.findAll()
                .stream()
                .map(socialAccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SocialAccountDTO> findById(Long id) {
        return socialAccountRepository.findById(id).map(socialAccountMapper::toDto);
    }

    @Override
    public SocialAccountDTO save(SocialAccountDTO socialAccountDTO) {
        SocialAccount socialAccount = socialAccountMapper.toEntity(socialAccountDTO);
        socialAccountRepository.save(socialAccount);
        return socialAccountMapper.toDto(socialAccount);
    }

    @Override
    public void deleteById(Long id) {
        socialAccountRepository.deleteById(id);
    }

    @Override
    public List<SocialAccountDTO> findByUserId(Long userId) {
        return socialAccountRepository.findAll()
                .stream()
                .map(socialAccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SocialAccountDTO> findByProviderAndProviderId(SocialProvider provider, String providerId) {
        return socialAccountRepository.findByProviderAndProviderId(provider, providerId).map(socialAccountMapper::toDto);
    }

    @Override
    public List<SocialAccountDTO> findByProvider(SocialProvider provider) {
        return socialAccountRepository.findByProvider(provider)
                .stream()
                .map(socialAccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SocialAccountDTO> findByUserIdAndProvider(Long userId, SocialProvider provider) {
        return socialAccountRepository.findByUser_IdAndProvider(userId, provider).map(socialAccountMapper::toDto);
    }

    @Override
    public Optional<SocialAccountDTO> findByProviderEmail(String email) {
        return socialAccountRepository.findByProviderEmail(email).map(socialAccountMapper::toDto);
    }
}
