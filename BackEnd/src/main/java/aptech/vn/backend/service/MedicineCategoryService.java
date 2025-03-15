package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineCategoryDTO;
import java.util.List;
import java.util.Optional;

public interface MedicineCategoryService {
    List<MedicineCategoryDTO.GetDto> findAll();
    Optional<MedicineCategoryDTO.GetDto> findById(Long id);
    MedicineCategoryDTO.GetDto saveOrUpdate(MedicineCategoryDTO.SaveDto medicineCategoryDTO);
    void deleteById(Long id);
    List<MedicineCategoryDTO.GetDto> findByMedicineId(Long medicineId);
    List<MedicineCategoryDTO.GetDto> findByCategoryId(Long categoryId);
    Optional<MedicineCategoryDTO.GetDto> findByMedicineIdAndCategoryId(Long medicineId, Long categoryId);
}