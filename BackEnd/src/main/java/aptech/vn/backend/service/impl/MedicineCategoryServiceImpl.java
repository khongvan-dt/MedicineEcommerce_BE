package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.MedicineCategoryDTO;
import aptech.vn.backend.entity.MedicineCategory;
import aptech.vn.backend.mapper.MedicineCategoryMapper;
import aptech.vn.backend.repository.MedicineCategoryRepository;
import aptech.vn.backend.service.MedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineCategoryServiceImpl implements MedicineCategoryService {

    private final MedicineCategoryRepository medicineCategoryRepository;
    private final MedicineCategoryMapper medicineCategoryMapper;

    @Autowired
    public MedicineCategoryServiceImpl(MedicineCategoryRepository medicineCategoryRepository, MedicineCategoryMapper medicineCategoryMapper) {
        this.medicineCategoryRepository = medicineCategoryRepository;
        this.medicineCategoryMapper = medicineCategoryMapper;
    }

    public List<MedicineCategoryDTO> findAll() {
        return medicineCategoryRepository.findAll().stream()
                .map(medicineCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MedicineCategoryDTO> findById(Long id) {
        return medicineCategoryRepository.findById(id)
                .map(medicineCategoryMapper::toDto);
    }

    public MedicineCategoryDTO save(MedicineCategoryDTO medicineCategoryDTO) {
        MedicineCategory medicineCategory = medicineCategoryMapper.toEntity(medicineCategoryDTO);
        MedicineCategory savedMedicineCategory = medicineCategoryRepository.save(medicineCategory);
        return medicineCategoryMapper.toDto(savedMedicineCategory);
    }

    public void deleteById(Long id) {
        medicineCategoryRepository.deleteById(id);
    }

    public List<MedicineCategoryDTO> findByMedicineId(Long medicineId) {
        return medicineCategoryRepository.findByMedicine_Id(medicineId).stream()
                .map(medicineCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MedicineCategoryDTO> findByCategoryId(Long categoryId) {
        return medicineCategoryRepository.findByCategory_Id(categoryId).stream()
                .map(medicineCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MedicineCategoryDTO> findByMedicineIdAndCategoryId(Long medicineId, Long categoryId) {
        return medicineCategoryRepository.findByMedicine_IdAndCategory_Id(medicineId, categoryId)
                .map(medicineCategoryMapper::toDto);
    }
}