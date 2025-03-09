package aptech.vn.backend.controller;

import aptech.vn.backend.dto.DoctorProfileDTO;
import aptech.vn.backend.service.DoctorProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctor-profiles")
public class DoctorProfileController {

    private final DoctorProfileService doctorProfileService;

    public DoctorProfileController(DoctorProfileService doctorProfileService) {
        this.doctorProfileService = doctorProfileService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorProfileDTO>> getAllDoctorProfiles() {
        List<DoctorProfileDTO> doctorProfiles = doctorProfileService.findAll();
        return ResponseEntity.ok(doctorProfiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorProfileDTO> getDoctorProfileById(@PathVariable Long id) {
        Optional<DoctorProfileDTO> doctorProfile = doctorProfileService.findById(id);
        return doctorProfile.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DoctorProfileDTO> createDoctorProfile(@RequestBody DoctorProfileDTO doctorProfileDTO) {
        DoctorProfileDTO savedDoctorProfile = doctorProfileService.save(doctorProfileDTO);
        return ResponseEntity.ok(savedDoctorProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorProfileDTO> updateDoctorProfile(@PathVariable Long id, @RequestBody DoctorProfileDTO doctorProfileDTO) {
        if (!doctorProfileService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        DoctorProfileDTO updatedDoctorProfile = doctorProfileService.save(doctorProfileDTO);
        return ResponseEntity.ok(updatedDoctorProfile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorProfile(@PathVariable Long id) {
        if (!doctorProfileService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        doctorProfileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/user/{userId}")
    public ResponseEntity<Optional<DoctorProfileDTO>> findByUserId(@PathVariable Long userId) {
        Optional<DoctorProfileDTO> doctorProfile = doctorProfileService.findByUserId(userId);
        return ResponseEntity.ok(doctorProfile);
    }

    @GetMapping("/search/specialization")
    public ResponseEntity<List<DoctorProfileDTO>> findBySpecialization(@RequestParam String specialization) {
        List<DoctorProfileDTO> doctorProfiles = doctorProfileService.findBySpecializationContaining(specialization);
        return ResponseEntity.ok(doctorProfiles);
    }

    @GetMapping("/search/workplace")
    public ResponseEntity<List<DoctorProfileDTO>> findByWorkplace(@RequestParam String workplace) {
        List<DoctorProfileDTO> doctorProfiles = doctorProfileService.findByWorkplaceContaining(workplace);
        return ResponseEntity.ok(doctorProfiles);
    }

    @GetMapping("/search/account-balance")
    public ResponseEntity<List<DoctorProfileDTO>> findByAccountBalanceGreaterThanEqual(@RequestParam BigDecimal amount) {
        List<DoctorProfileDTO> doctorProfiles = doctorProfileService.findByAccountBalanceGreaterThanEqual(amount);
        return ResponseEntity.ok(doctorProfiles);
    }
}
