package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineMediaDTO;
import aptech.vn.backend.entity.MediaType;

import java.util.List;
import java.util.Optional;

public interface MedicineMediaService {
    List<MedicineMediaDTO.GetDto> findAll();
    Optional<MedicineMediaDTO.GetDto> findById(Long id);
    MedicineMediaDTO.GetDto saveOrUpdate(MedicineMediaDTO.SaveDto medicineMediaDTO);
    void deleteById(Long id);
    List<MedicineMediaDTO.GetDto> findByMedicineId(Long medicineId);
    List<MedicineMediaDTO.GetDto> findByMediaType(MediaType mediaType);
    List<MedicineMediaDTO.GetDto> findByMedicineIdAndMediaType(Long medicineId, MediaType mediaType);
    Optional<MedicineMediaDTO.GetDto> findMainImageByMedicineId(Long medicineId);
}