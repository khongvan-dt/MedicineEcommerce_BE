package aptech.vn.backend.controller;

import aptech.vn.backend.dto.MedicineBatchDTO;
import aptech.vn.backend.service.MedicineBatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicine-batches")
public class MedicineBatchController {

    private final MedicineBatchService medicineBatchService;

    public MedicineBatchController(MedicineBatchService medicineBatchService) {
        this.medicineBatchService = medicineBatchService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineBatchDTO>> getAllMedicineBatches() {
        List<MedicineBatchDTO> medicineBatches = medicineBatchService.findAll();
        return ResponseEntity.ok(medicineBatches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineBatchDTO> getMedicineBatchById(@PathVariable Long id) {
        Optional<MedicineBatchDTO> medicineBatch = medicineBatchService.findById(id);
        return medicineBatch.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicineBatchDTO> createMedicineBatch(@RequestBody MedicineBatchDTO medicineBatchDTO) {
        MedicineBatchDTO savedMedicineBatch = medicineBatchService.save(medicineBatchDTO);
        return ResponseEntity.ok(savedMedicineBatch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineBatchDTO> updateMedicineBatch(@PathVariable Long id, @RequestBody MedicineBatchDTO medicineBatchDTO) {
        if (!medicineBatchService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        MedicineBatchDTO updatedMedicineBatch = medicineBatchService.save(medicineBatchDTO);
        return ResponseEntity.ok(updatedMedicineBatch);
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
    public ResponseEntity<Optional<MedicineBatchDTO>> findByBatchName(@RequestParam String batchName) {
        Optional<MedicineBatchDTO> medicineBatch = medicineBatchService.findByBatchName(batchName);
        return ResponseEntity.ok(medicineBatch);
    }

    @GetMapping("/search/medicine-code")
    public ResponseEntity<List<MedicineBatchDTO>> findByMedicineCode(@RequestParam String medicineCode) {
        List<MedicineBatchDTO> medicineBatches = medicineBatchService.findByMedicineCode(medicineCode);
        return ResponseEntity.ok(medicineBatches);
    }

    @GetMapping("/search/quantity")
    public ResponseEntity<List<MedicineBatchDTO>> findByQuantityGreaterThan(@RequestParam Integer quantity) {
        List<MedicineBatchDTO> medicineBatches = medicineBatchService.findByQuantityGreaterThan(quantity);
        return ResponseEntity.ok(medicineBatches);
    }

    @GetMapping("/search/expiry-date")
    public ResponseEntity<List<MedicineBatchDTO>> findByExpiryDateBefore(@RequestParam String date) {
        LocalDate expiryDate = LocalDate.parse(date);
        List<MedicineBatchDTO> medicineBatches = medicineBatchService.findByExpiryDateBefore(expiryDate);
        return ResponseEntity.ok(medicineBatches);
    }
}
