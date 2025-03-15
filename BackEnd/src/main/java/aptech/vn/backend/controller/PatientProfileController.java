package aptech.vn.backend.controller;

import aptech.vn.backend.dto.PatientProfileDTO;
import aptech.vn.backend.service.PatientProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/patient-profiles")
@CrossOrigin("*")
public class PatientProfileController {

    private final PatientProfileService patientProfileService;

    public PatientProfileController(PatientProfileService patientProfileService) {
        this.patientProfileService = patientProfileService;
    }

    @GetMapping
    public ResponseEntity<List<PatientProfileDTO.GetDto>> getAllProfiles() {
        List<PatientProfileDTO.GetDto> profiles = patientProfileService.findAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientProfileDTO.GetDto> getProfileById(@PathVariable Long id) {
        return patientProfileService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<PatientProfileDTO.GetDto> saveOrUpdateProfile(@RequestBody PatientProfileDTO.SaveDto patientProfileDTO) {
        PatientProfileDTO.GetDto savedProfile = patientProfileService.saveOrUpdate(patientProfileDTO);
        return ResponseEntity.ok(savedProfile);
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
    public ResponseEntity<PatientProfileDTO.GetDto> getProfileByUserId(@PathVariable Long userId) {
        return patientProfileService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-blood-type/{bloodType}")
    public ResponseEntity<List<PatientProfileDTO.GetDto>> getProfilesByBloodType(@PathVariable String bloodType) {
        List<PatientProfileDTO.GetDto> profiles = patientProfileService.findByBloodType(bloodType);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/by-medical-history")
    public ResponseEntity<List<PatientProfileDTO.GetDto>> getProfilesByMedicalHistory(@RequestParam String keyword) {
        List<PatientProfileDTO.GetDto> profiles = patientProfileService.findByMedicalHistoryContaining(keyword);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/by-allergies")
    public ResponseEntity<List<PatientProfileDTO.GetDto>> getProfilesByAllergies(@RequestParam String keyword) {
        List<PatientProfileDTO.GetDto> profiles = patientProfileService.findByAllergiesContaining(keyword);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/by-account-balance/{amount}")
    public ResponseEntity<List<PatientProfileDTO.GetDto>> getProfilesByAccountBalance(@PathVariable BigDecimal amount) {
        List<PatientProfileDTO.GetDto> profiles = patientProfileService.findByAccountBalanceGreaterThanEqual(amount);
        return ResponseEntity.ok(profiles);
    }
}