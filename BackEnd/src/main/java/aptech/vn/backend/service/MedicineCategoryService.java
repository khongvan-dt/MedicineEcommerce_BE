package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineCategoryDTO;
import java.util.List;
import java.util.Optional;

public interface MedicineCategoryService {
    List<MedicineCategoryDTO> findAll();
    Optional<MedicineCategoryDTO> findById(Long id);
    MedicineCategoryDTO save(MedicineCategoryDTO medicineCategoryDTO);
    void deleteById(Long id);
    List<MedicineCategoryDTO> findByMedicineId(Long medicineId);
    List<MedicineCategoryDTO> findByCategoryId(Long categoryId);
    Optional<MedicineCategoryDTO> findByMedicineIdAndCategoryId(Long medicineId, Long categoryId);
}