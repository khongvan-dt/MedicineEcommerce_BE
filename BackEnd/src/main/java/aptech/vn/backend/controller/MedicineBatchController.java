package aptech.vn.backend.controller;

import aptech.vn.backend.dto.MedicineBatchDTO;
import aptech.vn.backend.service.MedicineBatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/medicine-batches")
@CrossOrigin("*")
public class MedicineBatchController {

    private final MedicineBatchService medicineBatchService;

    public MedicineBatchController(MedicineBatchService medicineBatchService) {
        this.medicineBatchService = medicineBatchService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineBatchDTO.GetMedicineBatchDto>> getAllMedicineBatches() {
        List<MedicineBatchDTO.GetMedicineBatchDto> medicineBatches = medicineBatchService.findAll();
        return ResponseEntity.ok(medicineBatches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineBatchDTO.GetMedicineBatchDto> getMedicineBatchById(@PathVariable Long id) {
        return medicineBatchService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<MedicineBatchDTO.GetMedicineBatchDto> saveOrUpdateMedicineBatch(@RequestBody MedicineBatchDTO.SaveMedicineBatchDto medicineBatchDTO) {
        MedicineBatchDTO.GetMedicineBatchDto savedMedicineBatch = medicineBatchService.saveOrUpdate(medicineBatchDTO);
        return ResponseEntity.ok(savedMedicineBatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicineBatch(@PathVariable Long id) {
        if (!medicineBatchService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        medicineBatchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/batch-name")
    public ResponseEntity<MedicineBatchDTO.GetMedicineBatchDto> findByBatchName(@RequestParam String batchName) {
        return medicineBatchService.findByBatchName(batchName)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/medicine-code")
    public ResponseEntity<List<MedicineBatchDTO.GetMedicineBatchDto>> findByMedicineCode(@RequestParam String medicineCode) {
        List<MedicineBatchDTO.GetMedicineBatchDto> medicineBatches = medicineBatchService.findByMedicineCode(medicineCode);
        return ResponseEntity.ok(medicineBatches);
    }

    @GetMapping("/search/quantity")
    public ResponseEntity<List<MedicineBatchDTO.GetMedicineBatchDto>> findByQuantityGreaterThan(@RequestParam Integer quantity) {
        List<MedicineBatchDTO.GetMedicineBatchDto> medicineBatches = medicineBatchService.findByQuantityGreaterThan(quantity);
        return ResponseEntity.ok(medicineBatches);
    }

    @GetMapping("/search/expiry-date")
    public ResponseEntity<List<MedicineBatchDTO.GetMedicineBatchDto>> findByExpiryDateBefore(@RequestParam String date) {
        LocalDate expiryDate = LocalDate.parse(date);
        List<MedicineBatchDTO.GetMedicineBatchDto> medicineBatches = medicineBatchService.findByExpiryDateBefore(expiryDate);
        return ResponseEntity.ok(medicineBatches);
    }
}