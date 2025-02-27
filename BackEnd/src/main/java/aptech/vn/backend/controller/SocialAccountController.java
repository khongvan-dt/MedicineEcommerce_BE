package aptech.vn.backend.controller;

import aptech.vn.backend.entity.SocialAccount;
import aptech.vn.backend.entity.SocialProvider;
import aptech.vn.backend.service.SocialAccountService;
import aptech.vn.backend.service.impl.SocialAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/social-accounts")
public class SocialAccountController {

    private final SocialAccountService socialAccountService;

    @Autowired
    public SocialAccountController(SocialAccountServiceImpl socialAccountService) {
        this.socialAccountService = socialAccountService;
    }

    @GetMapping
    public ResponseEntity<List<SocialAccount>> getAllSocialAccounts() {
        return ResponseEntity.ok(socialAccountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocialAccount> getSocialAccountById(@PathVariable Long id) {
        return socialAccountService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SocialAccount>> getSocialAccountsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(socialAccountService.findByUserId(userId));
    }

    @GetMapping("/provider/{provider}")
    public ResponseEntity<List<SocialAccount>> getSocialAccountsByProvider(@PathVariable SocialProvider provider) {
        return ResponseEntity.ok(socialAccountService.findByProvider(provider));
    }

    @GetMapping("/provider/{provider}/id/{providerId}")
    public ResponseEntity<SocialAccount> getSocialAccountByProviderAndProviderId(
            @PathVariable SocialProvider provider,
            @PathVariable String providerId) {
        return socialAccountService.findByProviderAndProviderId(provider, providerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SocialAccount> createSocialAccount(@RequestBody SocialAccount socialAccount) {
        return new ResponseEntity<>(socialAccountService.save(socialAccount), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocialAccount> updateSocialAccount(@PathVariable Long id, @RequestBody SocialAccount socialAccount) {
        return socialAccountService.findById(id)
                .map(existingAccount -> {
                    socialAccount.setId(id);
                    return ResponseEntity.ok(socialAccountService.save(socialAccount));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/tokens")
    public ResponseEntity<String> updateTokens(
            @PathVariable Long id,
            @RequestBody Map<String, String> tokenMap) {

        String accessToken = tokenMap.get("accessToken");
        String refreshToken = tokenMap.get("refreshToken");

        if (accessToken == null) {
            return ResponseEntity.badRequest().body("Access token is required");
        }

        return socialAccountService.findById(id)
                .map(account -> {
                    boolean updated = socialAccountService.updateToken(id, accessToken, refreshToken);
                    if (updated) {
                        return ResponseEntity.ok("Tokens updated successfully");
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to update tokens");
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/link/{userId}")
    public ResponseEntity<String> linkAccountToUser(
            @PathVariable Long userId,
            @RequestBody SocialAccount socialAccount) {

        boolean linked = socialAccountService.linkAccountToUser(userId, socialAccount);
        if (linked) {
            return ResponseEntity.ok("Social account linked successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to link social account");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocialAccount(@PathVariable Long id) {
        return socialAccountService.findById(id)
                .map(account -> {
                    socialAccountService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}