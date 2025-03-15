package aptech.vn.backend.controller;

import aptech.vn.backend.dto.DoctorProfileDTO;
import aptech.vn.backend.service.DoctorProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/doctor-profiles")
@CrossOrigin("*")
public class DoctorProfileController {

    private final DoctorProfileService doctorProfileService;

    public DoctorProfileController(DoctorProfileService doctorProfileService) {
        this.doctorProfileService = doctorProfileService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorProfileDTO.GetDto>> getAllDoctorProfiles() {
        List<DoctorProfileDTO.GetDto> doctorProfiles = doctorProfileService.findAll();
        return ResponseEntity.ok(doctorProfiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorProfileDTO.GetDto> getDoctorProfileById(@PathVariable Long id) {
        return doctorProfileService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<DoctorProfileDTO.GetDto> saveOrUpdateDoctorProfile(@RequestBody DoctorProfileDTO.SaveDto doctorProfileDTO) {
        DoctorProfileDTO.GetDto savedDoctorProfile = doctorProfileService.saveOrUpdate(doctorProfileDTO);
        return ResponseEntity.ok(savedDoctorProfile);
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
    public ResponseEntity<DoctorProfileDTO.GetDto> findByUserId(@PathVariable Long userId) {
        return doctorProfileService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/specialization")
    public ResponseEntity<List<DoctorProfileDTO.GetDto>> findBySpecialization(@RequestParam String specialization) {
        List<DoctorProfileDTO.GetDto> doctorProfiles = doctorProfileService.findBySpecializationContaining(specialization);
        return ResponseEntity.ok(doctorProfiles);
    }

    @GetMapping("/search/workplace")
    public ResponseEntity<List<DoctorProfileDTO.GetDto>> findByWorkplace(@RequestParam String workplace) {
        List<DoctorProfileDTO.GetDto> doctorProfiles = doctorProfileService.findByWorkplaceContaining(workplace);
        return ResponseEntity.ok(doctorProfiles);
    }

    @GetMapping("/search/account-balance")
    public ResponseEntity<List<DoctorProfileDTO.GetDto>> findByAccountBalanceGreaterThanEqual(@RequestParam BigDecimal amount) {
        List<DoctorProfileDTO.GetDto> doctorProfiles = doctorProfileService.findByAccountBalanceGreaterThanEqual(amount);
        return ResponseEntity.ok(doctorProfiles);
    }
}