package aptech.vn.backend.controller;

import aptech.vn.backend.dto.PatientProfileDTO;
import aptech.vn.backend.service.PatientProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient-profiles")
@CrossOrigin("*")
public class PatientProfileController {

    private final PatientProfileService patientProfileService;

    public PatientProfileController(PatientProfileService patientProfileService) {
        this.patientProfileService = patientProfileService;
    }

    @GetMapping
    public ResponseEntity<List<PatientProfileDTO>> getAllProfiles() {
        List<PatientProfileDTO> profiles = patientProfileService.findAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientProfileDTO> getProfileById(@PathVariable Long id) {
        Optional<PatientProfileDTO> profile = patientProfileService.findById(id);
        return profile.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PatientProfileDTO> createProfile(@RequestBody PatientProfileDTO patientProfileDTO) {
        PatientProfileDTO savedProfile = patientProfileService.save(patientProfileDTO);
        return ResponseEntity.ok(savedProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientProfileDTO> updateProfile(@PathVariable Long id, @RequestBody PatientProfileDTO patientProfileDTO) {
        if (!patientProfileService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        PatientProfileDTO updatedProfile = patientProfileService.save(patientProfileDTO);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        if (!patientProfileService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        patientProfileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<PatientProfileDTO> getProfileByUserId(@PathVariable Long userId) {
        Optional<PatientProfileDTO> profile = patientProfileService.findByUserId(userId);
        return profile.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-blood-type/{bloodType}")
    public ResponseEntity<List<PatientProfileDTO>> getProfilesByBloodType(@PathVariable String bloodType) {
        List<PatientProfileDTO> profiles = patientProfileService.findByBloodType(bloodType);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/by-medical-history")
    public ResponseEntity<List<PatientProfileDTO>> getProfilesByMedicalHistory(@RequestParam String keyword) {
        List<PatientProfileDTO> profiles = patientProfileService.findByMedicalHistoryContaining(keyword);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/by-allergies")
    public ResponseEntity<List<PatientProfileDTO>> getProfilesByAllergies(@RequestParam String keyword) {
        List<PatientProfileDTO> profiles = patientProfileService.findByAllergiesContaining(keyword);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/by-account-balance/{amount}")
    public ResponseEntity<List<PatientProfileDTO>> getProfilesByAccountBalance(@PathVariable BigDecimal amount) {
        List<PatientProfileDTO> profiles = patientProfileService.findByAccountBalanceGreaterThanEqual(amount);
        return ResponseEntity.ok(profiles);
    }
}
