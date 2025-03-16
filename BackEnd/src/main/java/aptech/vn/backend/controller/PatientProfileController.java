package aptech.vn.backend.controller;

import aptech.vn.backend.dto.PatientProfileDTO;
import aptech.vn.backend.service.PatientProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    public ResponseEntity<List<PatientProfileDTO.GetPatientProfileDto>> getAllProfiles() {
        List<PatientProfileDTO.GetPatientProfileDto> profiles = patientProfileService.findAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientProfileDTO.GetPatientProfileDto> getProfileById(@PathVariable Long id) {
        return patientProfileService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/save")
    public ResponseEntity<PatientProfileDTO.GetPatientProfileDto> saveOrUpdateProfile(@RequestBody PatientProfileDTO.SavePatientProfileDto request) {
        PatientProfileDTO.GetPatientProfileDto savedProfile = patientProfileService.saveOrUpdate(request);
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
    public ResponseEntity<PatientProfileDTO.GetPatientProfileDto> getProfileByUserId(@PathVariable Long userId) {
        return patientProfileService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-blood-type/{bloodType}")
    public ResponseEntity<List<PatientProfileDTO.GetPatientProfileDto>> getProfilesByBloodType(@PathVariable String bloodType) {
        List<PatientProfileDTO.GetPatientProfileDto> profiles = patientProfileService.findByBloodType(bloodType);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/by-medical-history")
    public ResponseEntity<List<PatientProfileDTO.GetPatientProfileDto>> getProfilesByMedicalHistory(@RequestParam String keyword) {
        List<PatientProfileDTO.GetPatientProfileDto> profiles = patientProfileService.findByMedicalHistoryContaining(keyword);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/by-allergies")
    public ResponseEntity<List<PatientProfileDTO.GetPatientProfileDto>> getProfilesByAllergies(@RequestParam String keyword) {
        List<PatientProfileDTO.GetPatientProfileDto> profiles = patientProfileService.findByAllergiesContaining(keyword);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/by-account-balance/{amount}")
    public ResponseEntity<List<PatientProfileDTO.GetPatientProfileDto>> getProfilesByAccountBalance(@PathVariable BigDecimal amount) {
        List<PatientProfileDTO.GetPatientProfileDto> profiles = patientProfileService.findByAccountBalanceGreaterThanEqual(amount);
        return ResponseEntity.ok(profiles);
    }
}