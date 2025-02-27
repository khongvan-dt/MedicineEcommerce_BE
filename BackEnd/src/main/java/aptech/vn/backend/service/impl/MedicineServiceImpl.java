package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.repository.MedicineRepository;
import aptech.vn.backend.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Medicine save(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    @Override
    public Optional<Medicine> findById(Long id) {
        return medicineRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        medicineRepository.deleteById(id);
    }

    @Override
    public Optional<Medicine> findByCode(String code) {
        return medicineRepository.findByCode(code);
    }

    @Override
    public List<Medicine> findByName(String name) {
        return medicineRepository.findByName(name);
    }

    @Override
    public List<Medicine> findByBrandId(Long brandId) {
        return medicineRepository.findByBrandId(brandId);
    }

    @Override
    public List<Medicine> findByOrigin(String origin) {
        return medicineRepository.findByOrigin(origin);
    }

    @Override
    public List<Medicine> findByManufacturer(String manufacturer) {
        return medicineRepository.findByManufacturer(manufacturer);
    }

    @Override
    public List<Medicine> findByTypeId(Long typeId) {
        return medicineRepository.findByTypeId(typeId);
    }

    @Override
    public List<Medicine> findByNameContaining(String name) {
        return medicineRepository.findByNameContaining(name);
    }
}