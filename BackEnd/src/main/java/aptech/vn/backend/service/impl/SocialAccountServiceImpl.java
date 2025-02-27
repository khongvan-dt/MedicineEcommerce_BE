package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.SocialAccount;
import aptech.vn.backend.entity.SocialProvider;
import aptech.vn.backend.repository.SocialAccountRepository;
import aptech.vn.backend.service.SocialAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class SocialAccountServiceImpl implements SocialAccountService {

    private final SocialAccountRepository socialAccountRepository;

    public SocialAccountServiceImpl(SocialAccountRepository socialAccountRepository) {
        this.socialAccountRepository = socialAccountRepository;
    }

    @Override
    public SocialAccount save(SocialAccount socialAccount) {
        return socialAccountRepository.save(socialAccount);
    }

    @Override
    public Optional<SocialAccount> findById(Long id) {
        return socialAccountRepository.findById(id);
    }

    @Override
    public List<SocialAccount> findAll() {
        return socialAccountRepository.findAll();
    }

    @Override
    public Page<SocialAccount> findAll(Pageable pageable) {
        return socialAccountRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        socialAccountRepository.deleteById(id);
    }

    @Override
    public List<SocialAccount> findByUserId(Long userId) {
        return socialAccountRepository.findByUserId(userId);
    }

    @Override
    public Optional<SocialAccount> findByProviderAndProviderId(SocialProvider provider, String providerId) {
        return socialAccountRepository.findByProviderAndProviderId(provider, providerId);
    }

    @Override
    public List<SocialAccount> findByProvider(SocialProvider provider) {
        return socialAccountRepository.findByProvider(provider);
    }

    @Override
    public boolean linkAccountToUser(Long userId, SocialAccount socialAccount) {
        return false;
    }

    @Override
    public boolean updateToken(Long accountId, String accessToken, String refreshToken) {
        return false;
    }
}
