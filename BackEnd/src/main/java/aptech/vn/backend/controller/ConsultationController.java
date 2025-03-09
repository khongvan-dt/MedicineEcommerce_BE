package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ConsultationDTO;
import aptech.vn.backend.entity.ConsultationStatus;
import aptech.vn.backend.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultationDTO>> getAllConsultations() {
        List<ConsultationDTO> consultations = consultationService.findAll();
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable Long id) {
        Optional<ConsultationDTO> consultation = consultationService.findById(id);
        return consultation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConsultationDTO> createConsultation(@RequestBody ConsultationDTO consultationDTO) {
        ConsultationDTO savedConsultation = consultationService.save(consultationDTO);
        return ResponseEntity.ok(savedConsultation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultation(@PathVariable Long id, @RequestBody ConsultationDTO consultationDTO) {
        if (!consultationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ConsultationDTO updatedConsultation = consultationService.save(consultationDTO);
        return ResponseEntity.ok(updatedConsultation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        if (!consultationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        consultationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/patient/{patientId}")
    public ResponseEntity<List<ConsultationDTO>> findByPatientId(@PathVariable Long patientId) {
        List<ConsultationDTO> consultations = consultationService.findByPatientId(patientId);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/search/doctor/{doctorId}")
    public ResponseEntity<List<ConsultationDTO>> findByDoctorId(@PathVariable Long doctorId) {
        List<ConsultationDTO> consultations = consultationService.findByDoctorId(doctorId);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/search/status")
    public ResponseEntity<List<ConsultationDTO>> findByStatus(@RequestParam ConsultationStatus status) {
        List<ConsultationDTO> consultations = consultationService.findByStatus(status);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/search/patient-doctor")
    public ResponseEntity<List<ConsultationDTO>> findByPatientIdAndDoctorId(
            @RequestParam Long patientId, @RequestParam Long doctorId) {
        List<ConsultationDTO> consultations = consultationService.findByPatientIdAndDoctorId(patientId, doctorId);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/search/doctor-status")
    public ResponseEntity<List<ConsultationDTO>> findByDoctorIdAndStatus(
            @RequestParam Long doctorId, @RequestParam ConsultationStatus status) {
        List<ConsultationDTO> consultations = consultationService.findByDoctorIdAndStatus(doctorId, status);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/search/patient-status")
    public ResponseEntity<List<ConsultationDTO>> findByPatientIdAndStatus(
            @RequestParam Long patientId, @RequestParam ConsultationStatus status) {
        List<ConsultationDTO> consultations = consultationService.findByPatientIdAndStatus(patientId, status);
        return ResponseEntity.ok(consultations);
    }
}
