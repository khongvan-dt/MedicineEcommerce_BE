package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.MedicineCategory;
import aptech.vn.backend.repository.MedicineCategoryRepository;
import aptech.vn.backend.service.MedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicineCategoryServiceImpl implements MedicineCategoryService {

    private final MedicineCategoryRepository medicineCategoryRepository;

    @Autowired
    public MedicineCategoryServiceImpl(MedicineCategoryRepository medicineCategoryRepository) {
        this.medicineCategoryRepository = medicineCategoryRepository;
    }

    @Override
    public MedicineCategory save(MedicineCategory medicineCategory) {
        return medicineCategoryRepository.save(medicineCategory);
    }

    @Override
    public List<MedicineCategory> findAll() {
        return medicineCategoryRepository.findAll();
    }

    @Override
    public Optional<MedicineCategory> findById(Long id) {
        return medicineCategoryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        medicineCategoryRepository.deleteById(id);
    }

    @Override
    public List<MedicineCategory> findByMedicineId(Long medicineId) {
        return medicineCategoryRepository.findByMedicineId(medicineId);
    }

    @Override
    public List<MedicineCategory> findByCategoryId(Long categoryId) {
        return medicineCategoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public Optional<MedicineCategory> findByMedicineIdAndCategoryId(Long medicineId, Long categoryId) {
        return medicineCategoryRepository.findByMedicineIdAndCategoryId(medicineId, categoryId);
    }
}