package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineCategoryDTO;
import java.util.List;
import java.util.Optional;

public interface MedicineCategoryService {
    List<MedicineCategoryDTO.GetMedicineCategoryDto> findAll();
    Optional<MedicineCategoryDTO.GetMedicineCategoryDto> findById(Long id);
    MedicineCategoryDTO.GetMedicineCategoryDto saveOrUpdate(MedicineCategoryDTO.SaveMedicineCategoryDto medicineCategoryDTO);
    void deleteById(Long id);
    List<MedicineCategoryDTO.GetMedicineCategoryDto> findByMedicineId(Long medicineId);
    List<MedicineCategoryDTO.GetMedicineCategoryDto> findByCategoryId(Long categoryId);
    Optional<MedicineCategoryDTO.GetMedicineCategoryDto> findByMedicineIdAndCategoryId(Long medicineId, Long categoryId);
}