package aptech.vn.backend.controller;

import aptech.vn.backend.entity.PatientProfile;
import aptech.vn.backend.service.PatientProfileService;
import aptech.vn.backend.service.impl.PatientProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patient-profiles")
public class PatientProfileController {

    private final PatientProfileService patientProfileService;

    @Autowired
    public PatientProfileController(PatientProfileServiceImpl patientProfileService) {
        this.patientProfileService = patientProfileService;
    }

    @GetMapping
    public ResponseEntity<List<PatientProfile>> getAllPatientProfiles() {
        return ResponseEntity.ok(patientProfileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientProfile> getPatientProfileById(@PathVariable Long id) {
        return patientProfileService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PatientProfile> getPatientProfileByUserId(@PathVariable Long userId) {
        return patientProfileService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/blood-type/{bloodType}")
    public ResponseEntity<List<PatientProfile>> getPatientProfilesByBloodType(@PathVariable String bloodType) {
        return ResponseEntity.ok(patientProfileService.findByBloodType(bloodType));
    }

    @GetMapping("/medical-history")
    public ResponseEntity<List<PatientProfile>> searchMedicalHistory(@RequestParam String keyword) {
        return ResponseEntity.ok(patientProfileService.findByMedicalHistoryContaining(keyword));
    }

    @PostMapping
    public ResponseEntity<PatientProfile> createPatientProfile(@RequestBody PatientProfile patientProfile) {
        return new ResponseEntity<>(patientProfileService.save(patientProfile), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientProfile> updatePatientProfile(@PathVariable Long id, @RequestBody PatientProfile patientProfile) {
        return patientProfileService.findById(id)
                .map(existingProfile -> {
                    patientProfile.setId(id);
                    return ResponseEntity.ok(patientProfileService.save(patientProfile));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/balance")
    public ResponseEntity<String> updateAccountBalance(
            @PathVariable Long id,
            @RequestBody Map<String, BigDecimal> balanceMap) {

        BigDecimal amount = balanceMap.get("amount");
        if (amount == null) {
            return ResponseEntity.badRequest().body("Amount is required");
        }

        return patientProfileService.findById(id)
                .map(profile -> {
                    boolean updated = patientProfileService.updateAccountBalance(id, amount);
                    if (updated) {
                        return ResponseEntity.ok("Account balance updated successfully");
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to update account balance");
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientProfile(@PathVariable Long id) {
        return patientProfileService.findById(id)
                .map(profile -> {
                    patientProfileService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}