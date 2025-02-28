package aptech.vn.backend.controller;

import aptech.vn.backend.entity.MedicineBatch;
import aptech.vn.backend.service.MedicineBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/medicine-batches")
public class MedicineBatchController {

    @Autowired
    private MedicineBatchService medicineBatchService;

    @GetMapping
    public ResponseEntity<List<MedicineBatch>> getAllMedicineBatches() {
        List<MedicineBatch> medicineBatches = medicineBatchService.findAll();
        return new ResponseEntity<>(medicineBatches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineBatch> getMedicineBatchById(@PathVariable Long id) {
        return medicineBatchService.findById(id)
                .map(medicineBatch -> new ResponseEntity<>(medicineBatch, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MedicineBatch> createMedicineBatch(@RequestBody MedicineBatch medicineBatch) {
        MedicineBatch savedMedicineBatch = medicineBatchService.save(medicineBatch);
        return new ResponseEntity<>(savedMedicineBatch, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineBatch> updateMedicineBatch(@PathVariable Long id, @RequestBody MedicineBatch medicineBatch) {
        return medicineBatchService.findById(id)
                .map(existingMedicineBatch -> {
                    medicineBatch.setId(id);
                    MedicineBatch updatedMedicineBatch = medicineBatchService.save(medicineBatch);
                    return new ResponseEntity<>(updatedMedicineBatch, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicineBatch(@PathVariable Long id) {
        return medicineBatchService.findById(id)
                .map(medicineBatch -> {
                    medicineBatchService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/batch-name/{batchName}")
    public ResponseEntity<MedicineBatch> getMedicineBatchByBatchName(@PathVariable String batchName) {
        return medicineBatchService.findByBatchName(batchName)
                .map(medicineBatch -> new ResponseEntity<>(medicineBatch, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/medicine-code/{medicineCode}")
    public ResponseEntity<List<MedicineBatch>> getMedicineBatchesByMedicineCode(@PathVariable String medicineCode) {
        List<MedicineBatch> medicineBatches = medicineBatchService.findByMedicineCode(medicineCode);
        return new ResponseEntity<>(medicineBatches, HttpStatus.OK);
    }

    @GetMapping("/expiry-before")
    public ResponseEntity<List<MedicineBatch>> getMedicineBatchesExpiringBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<MedicineBatch> medicineBatches = medicineBatchService.findByExpiryDateBefore(date);
        return new ResponseEntity<>(medicineBatches, HttpStatus.OK);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<MedicineBatch>> getMedicineBatchesWithLowStock(@RequestParam Integer threshold) {
        List<MedicineBatch> medicineBatches = medicineBatchService.findByQuantityLessThan(threshold);
        return new ResponseEntity<>(medicineBatches, HttpStatus.OK);
    }

    @GetMapping("/expired")
    public ResponseEntity<List<MedicineBatch>> getExpiredMedicineBatches() {
        List<MedicineBatch> medicineBatches = medicineBatchService.findExpiredBatches();
        return new ResponseEntity<>(medicineBatches, HttpStatus.OK);
    }

    @GetMapping("/low-stock-threshold")
    public ResponseEntity<List<MedicineBatch>> getLowStockMedicineBatches(@RequestParam Integer threshold) {
        List<MedicineBatch> medicineBatches = medicineBatchService.findLowStockBatches(threshold);
        return new ResponseEntity<>(medicineBatches, HttpStatus.OK);
    }
}