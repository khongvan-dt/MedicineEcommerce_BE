package aptech.vn.backend.controller;

import aptech.vn.backend.dto.PrescriptionDTO;
import aptech.vn.backend.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin("*")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescriptions() {
        List<PrescriptionDTO> prescriptions = prescriptionService.findAll();
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(@PathVariable Long id) {
        Optional<PrescriptionDTO> prescription = prescriptionService.findById(id);
        return prescription.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PrescriptionDTO> createPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        PrescriptionDTO savedPrescription = prescriptionService.save(prescriptionDTO);
        return ResponseEntity.ok(savedPrescription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> updatePrescription(@PathVariable Long id, @RequestBody PrescriptionDTO prescriptionDTO) {
        if (!prescriptionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        PrescriptionDTO updatedPrescription = prescriptionService.save(prescriptionDTO);
        return ResponseEntity.ok(updatedPrescription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        if (!prescriptionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        prescriptionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsByDoctorId(@PathVariable Long doctorId) {
        List<PrescriptionDTO> prescriptions = prescriptionService.findByDoctorId(doctorId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsByPatientId(@PathVariable Long patientId) {
        List<PrescriptionDTO> prescriptions = prescriptionService.findByPatientId(patientId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-medicine/{medicineId}")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsByMedicineId(@PathVariable Long medicineId) {
        List<PrescriptionDTO> prescriptions = prescriptionService.findByMedicineId(medicineId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-patient-and-doctor")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsByPatientAndDoctor(
            @RequestParam Long patientId, @RequestParam Long doctorId) {
        List<PrescriptionDTO> prescriptions = prescriptionService.findByPatientIdAndDoctorId(patientId, doctorId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-patient-and-medicine")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsByPatientAndMedicine(
            @RequestParam Long patientId, @RequestParam Long medicineId) {
        List<PrescriptionDTO> prescriptions = prescriptionService.findByPatientIdAndMedicineId(patientId, medicineId);
        return ResponseEntity.ok(prescriptions);
    }
}
