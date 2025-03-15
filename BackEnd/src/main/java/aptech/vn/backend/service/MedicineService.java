package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineDTO;
import java.util.List;
import java.util.Optional;

public interface MedicineService {
    List<MedicineDTO.GetDto> findAll();
    Optional<MedicineDTO.GetDto> findById(Long id);
    MedicineDTO.GetDto saveOrUpdate(MedicineDTO.SaveDto medicineDTO);
    void softDeleteByIds(List<Long> ids);
    Optional<MedicineDTO.GetDto> findByCode(String code);
    List<MedicineDTO.GetDto> findByName(String name);
    List<MedicineDTO.GetDto> findByNameContaining(String namePattern);
    List<MedicineDTO.GetDto> findByBrandId(Long brandId);
    List<MedicineDTO.GetDto> findByOrigin(String origin);
    List<MedicineDTO.GetDto> findByManufacturer(String manufacturer);
}