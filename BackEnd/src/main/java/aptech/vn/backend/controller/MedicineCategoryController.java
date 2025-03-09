package aptech.vn.backend.controller;

import aptech.vn.backend.dto.MedicineCategoryDTO;
import aptech.vn.backend.service.MedicineCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicine-categories")
@CrossOrigin("*")
public class MedicineCategoryController {

    private final MedicineCategoryService medicineCategoryService;

    public MedicineCategoryController(MedicineCategoryService medicineCategoryService) {
        this.medicineCategoryService = medicineCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineCategoryDTO>> getAllMedicineCategories() {
        List<MedicineCategoryDTO> categories = medicineCategoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineCategoryDTO> getMedicineCategoryById(@PathVariable Long id) {
        Optional<MedicineCategoryDTO> category = medicineCategoryService.findById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicineCategoryDTO> createMedicineCategory(@RequestBody MedicineCategoryDTO medicineCategoryDTO) {
        MedicineCategoryDTO savedCategory = medicineCategoryService.save(medicineCategoryDTO);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineCategoryDTO> updateMedicineCategory(@PathVariable Long id, @RequestBody MedicineCategoryDTO medicineCategoryDTO) {
        if (!medicineCategoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        MedicineCategoryDTO updatedCategory = medicineCategoryService.save(medicineCategoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicineCategory(@PathVariable Long id) {
        if (!medicineCategoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        medicineCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-medicine/{medicineId}")
    public ResponseEntity<List<MedicineCategoryDTO>> getByMedicineId(@PathVariable Long medicineId) {
        List<MedicineCategoryDTO> categories = medicineCategoryService.findByMedicineId(medicineId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<MedicineCategoryDTO>> getByCategoryId(@PathVariable Long categoryId) {
        List<MedicineCategoryDTO> categories = medicineCategoryService.findByCategoryId(categoryId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/by-medicine-and-category")
    public ResponseEntity<MedicineCategoryDTO> getByMedicineAndCategory(@RequestParam Long medicineId, @RequestParam Long categoryId) {
        Optional<MedicineCategoryDTO> category = medicineCategoryService.findByMedicineIdAndCategoryId(medicineId, categoryId);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
