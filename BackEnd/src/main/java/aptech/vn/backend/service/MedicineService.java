package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineDTO;
import java.util.List;
import java.util.Optional;

public interface MedicineService {
    List<MedicineDTO> findAll();
    Optional<MedicineDTO> findById(Long id);
    MedicineDTO save(MedicineDTO medicineDTO);
    void deleteById(Long id);
    Optional<MedicineDTO> findByCode(String code);
    List<MedicineDTO> findByName(String name);
    List<MedicineDTO> findByNameContaining(String namePattern);
    List<MedicineDTO> findByBrandId(Long brandId);
    List<MedicineDTO> findByOrigin(String origin);
    List<MedicineDTO> findByManufacturer(String manufacturer);
}