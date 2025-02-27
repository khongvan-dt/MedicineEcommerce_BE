package aptech.vn.backend.service;

import aptech.vn.backend.entity.MedicineCategory;
import java.util.List;
import java.util.Optional;

public interface MedicineCategoryService {
    MedicineCategory save(MedicineCategory medicineCategory);
    List<MedicineCategory> findAll();
    Optional<MedicineCategory> findById(Long id);
    void deleteById(Long id);
    List<MedicineCategory> findByMedicineId(Long medicineId);
    List<MedicineCategory> findByCategoryId(Long categoryId);
    Optional<MedicineCategory> findByMedicineIdAndCategoryId(Long medicineId, Long categoryId);
}