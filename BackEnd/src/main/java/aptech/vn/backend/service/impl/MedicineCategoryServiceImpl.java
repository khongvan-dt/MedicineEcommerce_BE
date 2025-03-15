package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.MedicineCategoryDTO;
import aptech.vn.backend.entity.Category;
import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.entity.MedicineCategory;
import aptech.vn.backend.mapper.MedicineCategoryMapper;
import aptech.vn.backend.repository.CategoryRepository;
import aptech.vn.backend.repository.MedicineCategoryRepository;
import aptech.vn.backend.repository.MedicineRepository;
import aptech.vn.backend.service.MedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineCategoryServiceImpl implements MedicineCategoryService {

    private final MedicineCategoryRepository medicineCategoryRepository;
    private final MedicineRepository medicineRepository;
    private final CategoryRepository categoryRepository;
    private final MedicineCategoryMapper medicineCategoryMapper;

    @Autowired
    public MedicineCategoryServiceImpl(
            MedicineCategoryRepository medicineCategoryRepository,
            MedicineRepository medicineRepository,
            CategoryRepository categoryRepository,
            MedicineCategoryMapper medicineCategoryMapper) {
        this.medicineCategoryRepository = medicineCategoryRepository;
        this.medicineRepository = medicineRepository;
        this.categoryRepository = categoryRepository;
        this.medicineCategoryMapper = medicineCategoryMapper;
    }

    @Override
    public List<MedicineCategoryDTO.GetDto> findAll() {
        return medicineCategoryRepository.findAll().stream()
                .map(medicineCategoryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicineCategoryDTO.GetDto> findById(Long id) {
        return medicineCategoryRepository.findById(id)
                .map(medicineCategoryMapper::toGetDto);
    }

    @Override
    @Transactional
    public MedicineCategoryDTO.GetDto saveOrUpdate(MedicineCategoryDTO.SaveDto medicineCategoryDTO) {
        MedicineCategory medicineCategory;

        if (medicineCategoryDTO.getId() == null || medicineCategoryDTO.getId() == 0) {
            // INSERT case
            medicineCategory = new MedicineCategory();
            medicineCategory.setCreatedAt(LocalDateTime.now());
            medicineCategory.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<MedicineCategory> existingMedicineCategory = medicineCategoryRepository.findById(medicineCategoryDTO.getId());
            if (existingMedicineCategory.isEmpty()) {
                throw new RuntimeException("Medicine-Category relationship not found with ID: " + medicineCategoryDTO.getId());
            }
            medicineCategory = existingMedicineCategory.get();
            medicineCategory.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý Medicine relationship
        Medicine medicine = medicineRepository.findById(medicineCategoryDTO.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found with ID: " + medicineCategoryDTO.getMedicineId()));
        medicineCategory.setMedicine(medicine);

        // Xử lý Category relationship
        Category category = categoryRepository.findById(medicineCategoryDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + medicineCategoryDTO.getCategoryId()));
        medicineCategory.setCategory(category);

        MedicineCategory savedMedicineCategory = medicineCategoryRepository.save(medicineCategory);
        return medicineCategoryMapper.toGetDto(savedMedicineCategory);
    }

    @Override
    public void deleteById(Long id) {
        medicineCategoryRepository.deleteById(id);
    }

    @Override
    public List<MedicineCategoryDTO.GetDto> findByMedicineId(Long medicineId) {
        return medicineCategoryRepository.findByMedicine_Id(medicineId).stream()
                .map(medicineCategoryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineCategoryDTO.GetDto> findByCategoryId(Long categoryId) {
        return medicineCategoryRepository.findByCategory_Id(categoryId).stream()
                .map(medicineCategoryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicineCategoryDTO.GetDto> findByMedicineIdAndCategoryId(Long medicineId, Long categoryId) {
        return medicineCategoryRepository.findByMedicine_IdAndCategory_Id(medicineId, categoryId)
                .map(medicineCategoryMapper::toGetDto);
    }
}