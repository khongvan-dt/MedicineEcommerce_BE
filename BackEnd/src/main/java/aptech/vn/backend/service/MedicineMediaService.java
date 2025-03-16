package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineMediaDTO;
import aptech.vn.backend.entity.MediaType;

import java.util.List;
import java.util.Optional;

public interface MedicineMediaService {
    List<MedicineMediaDTO.GetMedicineMediaDto> findAll();
    Optional<MedicineMediaDTO.GetMedicineMediaDto> findById(Long id);
    MedicineMediaDTO.GetMedicineMediaDto saveOrUpdate(MedicineMediaDTO.SaveMedicineMediaDto medicineMediaDTO);
    void deleteById(Long id);
    List<MedicineMediaDTO.GetMedicineMediaDto> findByMedicineId(Long medicineId);
    List<MedicineMediaDTO.GetMedicineMediaDto> findByMediaType(MediaType mediaType);
    List<MedicineMediaDTO.GetMedicineMediaDto> findByMedicineIdAndMediaType(Long medicineId, MediaType mediaType);
    Optional<MedicineMediaDTO.GetMedicineMediaDto> findMainImageByMedicineId(Long medicineId);
}