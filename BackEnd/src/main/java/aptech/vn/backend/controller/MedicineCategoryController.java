package aptech.vn.backend.controller;

import aptech.vn.backend.dto.MedicineCategoryDTO;
import aptech.vn.backend.service.MedicineCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine-categories")
@CrossOrigin("*")
public class MedicineCategoryController {

    private final MedicineCategoryService medicineCategoryService;

    public MedicineCategoryController(MedicineCategoryService medicineCategoryService) {
        this.medicineCategoryService = medicineCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineCategoryDTO.GetDto>> getAllMedicineCategories() {
        List<MedicineCategoryDTO.GetDto> categories = medicineCategoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineCategoryDTO.GetDto> getMedicineCategoryById(@PathVariable Long id) {
        return medicineCategoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<MedicineCategoryDTO.GetDto> saveOrUpdateMedicineCategory(@RequestBody MedicineCategoryDTO.SaveDto medicineCategoryDTO) {
        MedicineCategoryDTO.GetDto savedCategory = medicineCategoryService.saveOrUpdate(medicineCategoryDTO);
        return ResponseEntity.ok(savedCategory);
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
    public ResponseEntity<List<MedicineCategoryDTO.GetDto>> getByMedicineId(@PathVariable Long medicineId) {
        List<MedicineCategoryDTO.GetDto> categories = medicineCategoryService.findByMedicineId(medicineId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<MedicineCategoryDTO.GetDto>> getByCategoryId(@PathVariable Long categoryId) {
        List<MedicineCategoryDTO.GetDto> categories = medicineCategoryService.findByCategoryId(categoryId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/by-medicine-and-category")
    public ResponseEntity<MedicineCategoryDTO.GetDto> getByMedicineAndCategory(
            @RequestParam Long medicineId,
            @RequestParam Long categoryId) {
        return medicineCategoryService.findByMedicineIdAndCategoryId(medicineId, categoryId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}