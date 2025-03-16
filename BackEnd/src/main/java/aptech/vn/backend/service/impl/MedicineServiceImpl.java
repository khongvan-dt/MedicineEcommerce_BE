package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.MedicineDTO;
import aptech.vn.backend.entity.Brand;
import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.mapper.MedicineMapper;
import aptech.vn.backend.repository.BrandRepository;
import aptech.vn.backend.repository.MedicineRepository;
import aptech.vn.backend.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final BrandRepository brandRepository;
    private final MedicineMapper medicineMapper;

    @Autowired
    public MedicineServiceImpl(
            MedicineRepository medicineRepository,
            BrandRepository brandRepository,
            MedicineMapper medicineMapper) {
        this.medicineRepository = medicineRepository;
        this.brandRepository = brandRepository;
        this.medicineMapper = medicineMapper;
    }

    @Override
    public List<MedicineDTO.GetMedicineDto> findAll() {
        return medicineRepository.findAllByDeletedAtIsNull().stream()
                .map(medicineMapper::toGetMedicineDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicineDTO.GetMedicineDto> findById(Long id) {
        return medicineRepository.findByIdAndDeletedAtIsNull(id)
                .map(medicineMapper::toGetMedicineDto);
    }

    @Override
    @Transactional
    public MedicineDTO.GetMedicineDto saveOrUpdate(MedicineDTO.SaveMedicineDto medicineDTO) {
        Medicine medicine;

        if (medicineDTO.getId() == null || medicineDTO.getId() == 0) {
            // INSERT case
            medicine = new Medicine();
            medicine.setCreatedAt(LocalDateTime.now());
            medicine.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Medicine> existingMedicine = medicineRepository.findByIdAndDeletedAtIsNull(medicineDTO.getId());
            if (existingMedicine.isEmpty()) {
                throw new RuntimeException("Medicine not found with ID: " + medicineDTO.getId());
            }
            medicine = existingMedicine.get();
            medicine.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý brand relationship nếu brandId được cung cấp
        if (medicineDTO.getBrandId() != null) {
            Brand brand = brandRepository.findById(medicineDTO.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found with ID: " + medicineDTO.getBrandId()));
            medicine.setBrand(brand);
        }

        // Cập nhật các trường cơ bản
        medicine.setCode(medicineDTO.getCode());
        medicine.setName(medicineDTO.getName());
        medicine.setOrigin(medicineDTO.getOrigin());
        medicine.setManufacturer(medicineDTO.getManufacturer());

        Medicine savedMedicine = medicineRepository.save(medicine);
        return medicineMapper.toGetMedicineDto(savedMedicine);
    }

    @Override
    @Transactional
    public void softDeleteByIds(List<Long> ids) {
        List<Medicine> medicines = medicineRepository.findAllById(ids);
        medicines.forEach(medicine -> medicine.setDeletedAt(LocalDateTime.now()));
        medicineRepository.saveAll(medicines);
    }

    @Override
    public Optional<MedicineDTO.GetMedicineDto> findByCode(String code) {
        return medicineRepository.findByCodeAndDeletedAtIsNull(code)
                .map(medicineMapper::toGetMedicineDto);
    }

    @Override
    public List<MedicineDTO.GetMedicineDto> findByName(String name) {
        return medicineRepository.findByNameAndDeletedAtIsNull(name).stream()
                .map(medicineMapper::toGetMedicineDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineDTO.GetMedicineDto> findByNameContaining(String namePattern) {
        return medicineRepository.findByNameContainingAndDeletedAtIsNull(namePattern).stream()
                .map(medicineMapper::toGetMedicineDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineDTO.GetMedicineDto> findByBrandId(Long brandId) {
        return medicineRepository.findByBrand_IdAndDeletedAtIsNull(brandId).stream()
                .map(medicineMapper::toGetMedicineDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineDTO.GetMedicineDto> findByOrigin(String origin) {
        return medicineRepository.findByOriginAndDeletedAtIsNull(origin).stream()
                .map(medicineMapper::toGetMedicineDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineDTO.GetMedicineDto> findByManufacturer(String manufacturer) {
        return medicineRepository.findByManufacturerAndDeletedAtIsNull(manufacturer).stream()
                .map(medicineMapper::toGetMedicineDto)
                .collect(Collectors.toList());
    }
}