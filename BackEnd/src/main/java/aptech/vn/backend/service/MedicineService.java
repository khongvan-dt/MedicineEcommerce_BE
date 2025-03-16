package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineDTO;
import java.util.List;
import java.util.Optional;

public interface MedicineService {
    List<MedicineDTO.GetMedicineDto> findAll();
    Optional<MedicineDTO.GetMedicineDto> findById(Long id);
    MedicineDTO.GetMedicineDto saveOrUpdate(MedicineDTO.SaveMedicineDto medicineDTO);
    void softDeleteByIds(List<Long> ids);
    Optional<MedicineDTO.GetMedicineDto> findByCode(String code);
    List<MedicineDTO.GetMedicineDto> findByName(String name);
    List<MedicineDTO.GetMedicineDto> findByNameContaining(String namePattern);
    List<MedicineDTO.GetMedicineDto> findByBrandId(Long brandId);
    List<MedicineDTO.GetMedicineDto> findByOrigin(String origin);
    List<MedicineDTO.GetMedicineDto> findByManufacturer(String manufacturer);
}