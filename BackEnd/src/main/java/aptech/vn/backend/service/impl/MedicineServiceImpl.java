package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.MedicineDTO;
import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.mapper.MedicineMapper;
import aptech.vn.backend.repository.MedicineRepository;
import aptech.vn.backend.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<MedicineDTO> findAll() {
        return medicineRepository.findAll().stream()
                .map(medicineMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MedicineDTO> findById(Long id) {
        return medicineRepository.findById(id)
                .map(medicineMapper::toDto);
    }

    public MedicineDTO save(MedicineDTO medicineDTO) {
        Medicine medicine = medicineMapper.toEntity(medicineDTO);
        Medicine savedMedicine = medicineRepository.save(medicine);
        return medicineMapper.toDto(savedMedicine);
    }

    public void deleteById(Long id) {
        medicineRepository.deleteById(id);
    }

    public Optional<MedicineDTO> findByCode(String code) {
        return medicineRepository.findByCode(code)
                .map(medicineMapper::toDto);
    }

    public List<MedicineDTO> findByName(String name) {
        return medicineRepository.findByName(name).stream()
                .map(medicineMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MedicineDTO> findByNameContaining(String namePattern) {
        return medicineRepository.findByNameContaining(namePattern).stream()
                .map(medicineMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MedicineDTO> findByBrandId(Long brandId) {
        return medicineRepository.findByBrand_Id(brandId).stream()
                .map(medicineMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MedicineDTO> findByOrigin(String origin) {
        return medicineRepository.findByOrigin(origin).stream()
                .map(medicineMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MedicineDTO> findByManufacturer(String manufacturer) {
        return medicineRepository.findByManufacturer(manufacturer).stream()
                .map(medicineMapper::toDto)
                .collect(Collectors.toList());
    }
}