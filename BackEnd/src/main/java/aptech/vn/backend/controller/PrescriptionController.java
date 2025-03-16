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
    public ResponseEntity<List<PrescriptionDTO.GetPrescriptionDto>> getAllPrescriptions() {
        List<PrescriptionDTO.GetPrescriptionDto> prescriptions = prescriptionService.findAll();
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO.GetPrescriptionDto> getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<PrescriptionDTO.GetPrescriptionDto> saveOrUpdatePrescription(@RequestBody PrescriptionDTO.SavePrescriptionDto prescriptionDTO) {
        PrescriptionDTO.GetPrescriptionDto savedPrescription = prescriptionService.saveOrUpdate(prescriptionDTO);
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
    public ResponseEntity<List<PrescriptionDTO.GetPrescriptionDto>> getPrescriptionsByDoctorId(@PathVariable Long doctorId) {
        List<PrescriptionDTO.GetPrescriptionDto> prescriptions = prescriptionService.findByDoctorId(doctorId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<PrescriptionDTO.GetPrescriptionDto>> getPrescriptionsByPatientId(@PathVariable Long patientId) {
        List<PrescriptionDTO.GetPrescriptionDto> prescriptions = prescriptionService.findByPatientId(patientId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-medicine/{medicineId}")
    public ResponseEntity<List<PrescriptionDTO.GetPrescriptionDto>> getPrescriptionsByMedicineId(@PathVariable Long medicineId) {
        List<PrescriptionDTO.GetPrescriptionDto> prescriptions = prescriptionService.findByMedicineId(medicineId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-patient-and-doctor")
    public ResponseEntity<List<PrescriptionDTO.GetPrescriptionDto>> getPrescriptionsByPatientAndDoctor(
            @RequestParam Long patientId, @RequestParam Long doctorId) {
        List<PrescriptionDTO.GetPrescriptionDto> prescriptions = prescriptionService.findByPatientIdAndDoctorId(patientId, doctorId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/by-patient-and-medicine")
    public ResponseEntity<List<PrescriptionDTO.GetPrescriptionDto>> getPrescriptionsByPatientAndMedicine(
            @RequestParam Long patientId, @RequestParam Long medicineId) {
        List<PrescriptionDTO.GetPrescriptionDto> prescriptions = prescriptionService.findByPatientIdAndMedicineId(patientId, medicineId);
        return ResponseEntity.ok(prescriptions);
    }
}