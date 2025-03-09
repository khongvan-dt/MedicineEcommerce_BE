package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.MedicineDTO;
import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.mapper.MedicineMapper;
import aptech.vn.backend.repository.MedicineRepository;
import aptech.vn.backend.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, MedicineMapper medicineMapper) {
        this.medicineRepository = medicineRepository;
        this.medicineMapper = medicineMapper;
    }

    @Override
    public List<MedicineDTO.GetDto> findAll() {
        return medicineRepository.findAllByDeletedAtIsNull().stream()
                .map(medicineMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicineDTO.GetDto> findById(Long id) {
        return medicineRepository.findByIdAndDeletedAtIsNull(id)
                .map(medicineMapper::toGetDto);
    }

    @Override
    public MedicineDTO.InsertDto save(MedicineDTO.InsertDto medicineDTO) {
        Medicine medicine = medicineMapper.toEntity(medicineDTO);
        Medicine savedMedicine = medicineRepository.save(medicine);
        return medicineMapper.toInsertDto(savedMedicine);
    }

    @Override
    public void softDeleteByIds(List<Long> ids) {
        List<Medicine> medicines = medicineRepository.findAllById(ids);
        medicines.forEach(medicine -> medicine.setDeletedAt(LocalDateTime.now()));
        medicineRepository.saveAll(medicines);
    }

    @Override
    public Optional<MedicineDTO.GetDto> findByCode(String code) {
        return medicineRepository.findByCodeAndDeletedAtIsNull(code)
                .map(medicineMapper::toGetDto);
    }

    @Override
    public List<MedicineDTO.GetDto> findByName(String name) {
        return medicineRepository.findByNameAndDeletedAtIsNull(name).stream()
                .map(medicineMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineDTO.GetDto> findByNameContaining(String namePattern) {
        return medicineRepository.findByNameContainingAndDeletedAtIsNull(namePattern).stream()
                .map(medicineMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineDTO.GetDto> findByBrandId(Long brandId) {
        return medicineRepository.findByBrandIdAndDeletedAtIsNull(brandId).stream()
                .map(medicineMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineDTO.GetDto> findByOrigin(String origin) {
        return medicineRepository.findByOriginAndDeletedAtIsNull(origin).stream()
                .map(medicineMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineDTO.GetDto> findByManufacturer(String manufacturer) {
        return medicineRepository.findByManufacturerAndDeletedAtIsNull(manufacturer).stream()
                .map(medicineMapper::toGetDto)
                .collect(Collectors.toList());
    }
}
