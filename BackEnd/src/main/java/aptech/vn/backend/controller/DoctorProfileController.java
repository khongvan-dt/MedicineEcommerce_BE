package aptech.vn.backend.controller;

import aptech.vn.backend.entity.DoctorProfile;
import aptech.vn.backend.service.DoctorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor-profiles")
public class DoctorProfileController {

    @Autowired
    private DoctorProfileService doctorProfileService;

    @GetMapping
    public ResponseEntity<List<DoctorProfile>> getAllDoctorProfiles() {
        List<DoctorProfile> doctorProfiles = doctorProfileService.findAll();
        return new ResponseEntity<>(doctorProfiles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorProfile> getDoctorProfileById(@PathVariable Long id) {
        return doctorProfileService.findById(id)
                .map(doctorProfile -> new ResponseEntity<>(doctorProfile, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DoctorProfile> createDoctorProfile(@RequestBody DoctorProfile doctorProfile) {
        DoctorProfile savedDoctorProfile = doctorProfileService.save(doctorProfile);
        return new ResponseEntity<>(savedDoctorProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorProfile> updateDoctorProfile(@PathVariable Long id, @RequestBody DoctorProfile doctorProfile) {
        return doctorProfileService.findById(id)
                .map(existingDoctorProfile -> {
                    doctorProfile.setId(id);
                    DoctorProfile updatedDoctorProfile = doctorProfileService.save(doctorProfile);
                    return new ResponseEntity<>(updatedDoctorProfile, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorProfile(@PathVariable Long id) {
        return doctorProfileService.findById(id)
                .map(doctorProfile -> {
                    doctorProfileService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<DoctorProfile> getDoctorProfileByUserId(@PathVariable Long userId) {
        return doctorProfileService.findByUserId(userId)
                .map(doctorProfile -> new ResponseEntity<>(doctorProfile, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<DoctorProfile>> getDoctorProfilesBySpecialization(@PathVariable String specialization) {
        List<DoctorProfile> doctorProfiles = doctorProfileService.findBySpecialization(specialization);
        return new ResponseEntity<>(doctorProfiles, HttpStatus.OK);
    }

    @GetMapping("/workplace/{workplace}")
    public ResponseEntity<List<DoctorProfile>> getDoctorProfilesByWorkplace(@PathVariable String workplace) {
        List<DoctorProfile> doctorProfiles = doctorProfileService.findByWorkplace(workplace);
        return new ResponseEntity<>(doctorProfiles, HttpStatus.OK);
    }

    @GetMapping("/search/specialization")
    public ResponseEntity<List<DoctorProfile>> getDoctorProfilesBySpecializationContaining(@RequestParam String specialization) {
        List<DoctorProfile> doctorProfiles = doctorProfileService.findBySpecializationContaining(specialization);
        return new ResponseEntity<>(doctorProfiles, HttpStatus.OK);
    }
}