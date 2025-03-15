package aptech.vn.backend.controller;

import aptech.vn.backend.dto.SocialAccountDTO;
import aptech.vn.backend.entity.SocialProvider;
import aptech.vn.backend.service.SocialAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social-accounts")
@CrossOrigin("*")
public class SocialAccountController {

    private final SocialAccountService socialAccountService;

    public SocialAccountController(SocialAccountService socialAccountService) {
        this.socialAccountService = socialAccountService;
    }

    @GetMapping
    public ResponseEntity<List<SocialAccountDTO.GetDto>> getAllSocialAccounts() {
        List<SocialAccountDTO.GetDto> accounts = socialAccountService.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocialAccountDTO.GetDto> getSocialAccountById(@PathVariable Long id) {
        return socialAccountService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<SocialAccountDTO.GetDto> saveOrUpdateSocialAccount(@RequestBody SocialAccountDTO.SaveDto socialAccountDTO) {
        SocialAccountDTO.GetDto savedAccount = socialAccountService.saveOrUpdate(socialAccountDTO);
        return ResponseEntity.ok(savedAccount);
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
    public ResponseEntity<List<SocialAccountDTO.GetDto>> getSocialAccountsByUserId(@PathVariable Long userId) {
        List<SocialAccountDTO.GetDto> accounts = socialAccountService.findByUserId(userId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/by-provider-and-id")
    public ResponseEntity<SocialAccountDTO.GetDto> getSocialAccountByProviderAndId(
            @RequestParam SocialProvider provider,
            @RequestParam String providerId) {
        return socialAccountService.findByProviderAndProviderId(provider, providerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-provider/{provider}")
    public ResponseEntity<List<SocialAccountDTO.GetDto>> getSocialAccountsByProvider(@PathVariable SocialProvider provider) {
        List<SocialAccountDTO.GetDto> accounts = socialAccountService.findByProvider(provider);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/by-user-and-provider")
    public ResponseEntity<SocialAccountDTO.GetDto> getSocialAccountByUserIdAndProvider(
            @RequestParam Long userId,
            @RequestParam SocialProvider provider) {
        return socialAccountService.findByUserIdAndProvider(userId, provider)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<SocialAccountDTO.GetDto> getSocialAccountByEmail(@PathVariable String email) {
        return socialAccountService.findByProviderEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}