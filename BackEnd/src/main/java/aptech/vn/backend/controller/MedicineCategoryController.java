package aptech.vn.backend.controller;

import aptech.vn.backend.entity.MedicineCategory;
import aptech.vn.backend.service.MedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine-categories")
public class MedicineCategoryController {

    @Autowired
    private MedicineCategoryService medicineCategoryService;

    @GetMapping
    public ResponseEntity<List<MedicineCategory>> getAllMedicineCategories() {
        List<MedicineCategory> medicineCategories = medicineCategoryService.findAll();
        return new ResponseEntity<>(medicineCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineCategory> getMedicineCategoryById(@PathVariable Long id) {
        return medicineCategoryService.findById(id)
                .map(medicineCategory -> new ResponseEntity<>(medicineCategory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MedicineCategory> createMedicineCategory(@RequestBody MedicineCategory medicineCategory) {
        MedicineCategory savedMedicineCategory = medicineCategoryService.save(medicineCategory);
        return new ResponseEntity<>(savedMedicineCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineCategory> updateMedicineCategory(@PathVariable Long id, @RequestBody MedicineCategory medicineCategory) {
        return medicineCategoryService.findById(id)
                .map(existingMedicineCategory -> {
                    medicineCategory.setId(id);
                    MedicineCategory updatedMedicineCategory = medicineCategoryService.save(medicineCategory);
                    return new ResponseEntity<>(updatedMedicineCategory, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicineCategory(@PathVariable Long id) {
        return medicineCategoryService.findById(id)
                .map(medicineCategory -> {
                    medicineCategoryService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<MedicineCategory>> getMedicineCategoriesByMedicineId(@PathVariable Long medicineId) {
        List<MedicineCategory> medicineCategories = medicineCategoryService.findByMedicineId(medicineId);
        return new ResponseEntity<>(medicineCategories, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<MedicineCategory>> getMedicineCategoriesByCategoryId(@PathVariable Long categoryId) {
        List<MedicineCategory> medicineCategories = medicineCategoryService.findByCategoryId(categoryId);
        return new ResponseEntity<>(medicineCategories, HttpStatus.OK);
    }

    @GetMapping("/medicine/{medicineId}/category/{categoryId}")
    public ResponseEntity<MedicineCategory> getMedicineCategoryByMedicineIdAndCategoryId(
            @PathVariable Long medicineId, @PathVariable Long categoryId) {
        return medicineCategoryService.findByMedicineIdAndCategoryId(medicineId, categoryId)
                .map(medicineCategory -> new ResponseEntity<>(medicineCategory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}