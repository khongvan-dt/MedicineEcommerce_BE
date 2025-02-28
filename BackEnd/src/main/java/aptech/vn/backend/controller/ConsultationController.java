package aptech.vn.backend.controller;

import aptech.vn.backend.entity.Consultation;
import aptech.vn.backend.entity.ConsultationStatus;
import aptech.vn.backend.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @GetMapping
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        List<Consultation> consultations = consultationService.findAll();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable Long id) {
        return consultationService.findById(id)
                .map(consultation -> new ResponseEntity<>(consultation, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Consultation> createConsultation(@RequestBody Consultation consultation) {
        Consultation savedConsultation = consultationService.save(consultation);
        return new ResponseEntity<>(savedConsultation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultation> updateConsultation(@PathVariable Long id, @RequestBody Consultation consultation) {
        return consultationService.findById(id)
                .map(existingConsultation -> {
                    consultation.setId(id);
                    Consultation updatedConsultation = consultationService.save(consultation);
                    return new ResponseEntity<>(updatedConsultation, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        return consultationService.findById(id)
                .map(consultation -> {
                    consultationService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Consultation>> getConsultationsByPatientId(@PathVariable Long patientId) {
        List<Consultation> consultations = consultationService.findByPatientId(patientId);
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Consultation>> getConsultationsByDoctorId(@PathVariable Long doctorId) {
        List<Consultation> consultations = consultationService.findByDoctorId(doctorId);
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Consultation>> getConsultationsByStatus(@PathVariable ConsultationStatus status) {
        List<Consultation> consultations = consultationService.findByStatus(status);
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}/status/{status}")
    public ResponseEntity<List<Consultation>> getConsultationsByPatientIdAndStatus(
            @PathVariable Long patientId, @PathVariable ConsultationStatus status) {
        List<Consultation> consultations = consultationService.findByPatientIdAndStatus(patientId, status);
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}/status/{status}")
    public ResponseEntity<List<Consultation>> getConsultationsByDoctorIdAndStatus(
            @PathVariable Long doctorId, @PathVariable ConsultationStatus status) {
        List<Consultation> consultations = consultationService.findByDoctorIdAndStatus(doctorId, status);
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }
}