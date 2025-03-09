package aptech.vn.backend.controller;

import aptech.vn.backend.dto.SocialAccountDTO;
import aptech.vn.backend.entity.SocialProvider;
import aptech.vn.backend.service.SocialAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/social-accounts")
public class SocialAccountController {

    private final SocialAccountService socialAccountService;

    public SocialAccountController(SocialAccountService socialAccountService) {
        this.socialAccountService = socialAccountService;
    }

    @GetMapping
    public ResponseEntity<List<SocialAccountDTO>> getAllSocialAccounts() {
        List<SocialAccountDTO> accounts = socialAccountService.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocialAccountDTO> getSocialAccountById(@PathVariable Long id) {
        Optional<SocialAccountDTO> account = socialAccountService.findById(id);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SocialAccountDTO> createSocialAccount(@RequestBody SocialAccountDTO socialAccountDTO) {
        SocialAccountDTO savedAccount = socialAccountService.save(socialAccountDTO);
        return ResponseEntity.ok(savedAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocialAccountDTO> updateSocialAccount(@PathVariable Long id, @RequestBody SocialAccountDTO socialAccountDTO) {
        if (!socialAccountService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        SocialAccountDTO updatedAccount = socialAccountService.save(socialAccountDTO);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocialAccount(@PathVariable Long id) {
        if (!socialAccountService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        socialAccountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<SocialAccountDTO>> getSocialAccountsByUserId(@PathVariable Long userId) {
        List<SocialAccountDTO> accounts = socialAccountService.findByUserId(userId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/by-provider-and-id")
    public ResponseEntity<SocialAccountDTO> getSocialAccountByProviderAndId(
            @RequestParam SocialProvider provider,
            @RequestParam String providerId) {
        Optional<SocialAccountDTO> account = socialAccountService.findByProviderAndProviderId(provider, providerId);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-provider/{provider}")
    public ResponseEntity<List<SocialAccountDTO>> getSocialAccountsByProvider(@PathVariable SocialProvider provider) {
        List<SocialAccountDTO> accounts = socialAccountService.findByProvider(provider);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/by-user-and-provider")
    public ResponseEntity<SocialAccountDTO> getSocialAccountByUserIdAndProvider(
            @RequestParam Long userId,
            @RequestParam SocialProvider provider) {
        Optional<SocialAccountDTO> account = socialAccountService.findByUserIdAndProvider(userId, provider);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<SocialAccountDTO> getSocialAccountByEmail(@PathVariable String email) {
        Optional<SocialAccountDTO> account = socialAccountService.findByProviderEmail(email);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
