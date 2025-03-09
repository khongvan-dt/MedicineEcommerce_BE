package aptech.vn.backend.controller;

import aptech.vn.backend.dto.MedicineDTO;
import aptech.vn.backend.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineDTO>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineDTO> getMedicineById(@PathVariable Long id) {
        Optional<MedicineDTO> medicine = medicineService.findById(id);
        return medicine.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicineDTO> createMedicine(@RequestBody MedicineDTO medicineDTO) {
        return ResponseEntity.ok(medicineService.save(medicineDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineDTO> updateMedicine(@PathVariable Long id, @RequestBody MedicineDTO medicineDTO) {
        if (!medicineService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicineService.save(medicineDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        if (!medicineService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        medicineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-code")
    public ResponseEntity<MedicineDTO> getMedicineByCode(@RequestParam String code) {
        Optional<MedicineDTO> medicine = medicineService.findByCode(code);
        return medicine.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<MedicineDTO>> getMedicineByName(@RequestParam String name) {
        return ResponseEntity.ok(medicineService.findByName(name));
    }

    @GetMapping("/by-name-containing")
    public ResponseEntity<List<MedicineDTO>> getMedicineByNameContaining(@RequestParam String namePattern) {
        return ResponseEntity.ok(medicineService.findByNameContaining(namePattern));
    }

    @GetMapping("/by-brand")
    public ResponseEntity<List<MedicineDTO>> getMedicineByBrand(@RequestParam Long brandId) {
        return ResponseEntity.ok(medicineService.findByBrandId(brandId));
    }

    @GetMapping("/by-origin")
    public ResponseEntity<List<MedicineDTO>> getMedicineByOrigin(@RequestParam String origin) {
        return ResponseEntity.ok(medicineService.findByOrigin(origin));
    }

    @GetMapping("/by-manufacturer")
    public ResponseEntity<List<MedicineDTO>> getMedicineByManufacturer(@RequestParam String manufacturer) {
        return ResponseEntity.ok(medicineService.findByManufacturer(manufacturer));
    }
}
