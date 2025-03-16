package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ConsultationDTO;
import aptech.vn.backend.entity.ConsultationStatus;
import aptech.vn.backend.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
@CrossOrigin("*")
public class ConsultationController {

    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultationDTO.GetConsultationDto>> getAllConsultations() {
        List<ConsultationDTO.GetConsultationDto> consultations = consultationService.findAll();
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO.GetConsultationDto> getConsultationById(@PathVariable Long id) {
        return consultationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ConsultationDTO.GetConsultationDto> saveOrUpdateConsultation(@RequestBody ConsultationDTO.SaveConsultationDto consultationDTO) {
        ConsultationDTO.GetConsultationDto savedConsultation = consultationService.saveOrUpdate(consultationDTO);
        return ResponseEntity.ok(savedConsultation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        if (!consultationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        consultationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ConsultationDTO.GetConsultationDto>> getConsultationsByPatientId(@PathVariable Long patientId) {
        List<ConsultationDTO.GetConsultationDto> consultations = consultationService.findByPatientId(patientId);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ConsultationDTO.GetConsultationDto>> getConsultationsByDoctorId(@PathVariable Long doctorId) {
        List<ConsultationDTO.GetConsultationDto> consultations = consultationService.findByDoctorId(doctorId);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConsultationDTO.GetConsultationDto>> getConsultationsByStatus(@PathVariable ConsultationStatus status) {
        List<ConsultationDTO.GetConsultationDto> consultations = consultationService.findByStatus(status);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/patient/{patientId}/doctor/{doctorId}")
    public ResponseEntity<List<ConsultationDTO.GetConsultationDto>> getConsultationsByPatientAndDoctor(
            @PathVariable Long patientId,
            @PathVariable Long doctorId) {
        List<ConsultationDTO.GetConsultationDto> consultations = consultationService.findByPatientIdAndDoctorId(patientId, doctorId);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/doctor/{doctorId}/status/{status}")
    public ResponseEntity<List<ConsultationDTO.GetConsultationDto>> getConsultationsByDoctorAndStatus(
            @PathVariable Long doctorId,
            @PathVariable ConsultationStatus status) {
        List<ConsultationDTO.GetConsultationDto> consultations = consultationService.findByDoctorIdAndStatus(doctorId, status);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/patient/{patientId}/status/{status}")
    public ResponseEntity<List<ConsultationDTO.GetConsultationDto>> getConsultationsByPatientAndStatus(
            @PathVariable Long patientId,
            @PathVariable ConsultationStatus status) {
        List<ConsultationDTO.GetConsultationDto> consultations = consultationService.findByPatientIdAndStatus(patientId, status);
        return ResponseEntity.ok(consultations);
    }
}