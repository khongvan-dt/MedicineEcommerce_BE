package aptech.vn.backend.controller;

import aptech.vn.backend.dto.PrescriptionDTO;
import aptech.vn.backend.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin("*")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionDTO.GetDto>> getAllPrescriptions() {
        List<PrescriptionDTO.GetDto> prescriptions = prescriptionService.findAll();
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO.GetDto> getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<PrescriptionDTO.GetDto> saveOrUpdatePrescription(@RequestBody PrescriptionDTO.SaveDto prescriptionDTO) {
        PrescriptionDTO.GetDto savedPrescription = prescriptionService.saveOrUpdate(prescriptionDTO);
        return ResponseEntity.ok(savedPrescription);
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
    public ResponseEntity<List<PrescriptionDTO.GetDto>> getPrescriptionsByDoctorId(@PathVariable Long doctorId) {
        List<PrescriptionDTO.GetDto> prescriptions = prescriptionService.findByDoctorId(doctorId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<PrescriptionDTO.GetDto>> getPrescriptionsByPatientId(@PathVariable Long patientId) {
        List<PrescriptionDTO.GetDto> prescriptions = prescriptionService.findByPatientId(patientId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-medicine/{medicineId}")
    public ResponseEntity<List<PrescriptionDTO.GetDto>> getPrescriptionsByMedicineId(@PathVariable Long medicineId) {
        List<PrescriptionDTO.GetDto> prescriptions = prescriptionService.findByMedicineId(medicineId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-patient-and-doctor")
    public ResponseEntity<List<PrescriptionDTO.GetDto>> getPrescriptionsByPatientAndDoctor(
            @RequestParam Long patientId, @RequestParam Long doctorId) {
        List<PrescriptionDTO.GetDto> prescriptions = prescriptionService.findByPatientIdAndDoctorId(patientId, doctorId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-patient-and-medicine")
    public ResponseEntity<List<PrescriptionDTO.GetDto>> getPrescriptionsByPatientAndMedicine(
            @RequestParam Long patientId, @RequestParam Long medicineId) {
        List<PrescriptionDTO.GetDto> prescriptions = prescriptionService.findByPatientIdAndMedicineId(patientId, medicineId);
        return ResponseEntity.ok(prescriptions);
    }
}