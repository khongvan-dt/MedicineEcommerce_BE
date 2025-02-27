package aptech.vn.backend.service;

import aptech.vn.backend.entity.Medicine;
import java.util.List;
import java.util.Optional;

public interface MedicineService {
    Medicine save(Medicine medicine);
    List<Medicine> findAll();
    Optional<Medicine> findById(Long id);
    void deleteById(Long id);
    Optional<Medicine> findByCode(String code);
    List<Medicine> findByName(String name);
    List<Medicine> findByBrandId(Long brandId);
    List<Medicine> findByOrigin(String origin);
    List<Medicine> findByManufacturer(String manufacturer);
    List<Medicine> findByTypeId(Long typeId);
    List<Medicine> findByNameContaining(String name);
}