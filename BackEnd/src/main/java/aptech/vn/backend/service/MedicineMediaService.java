package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineMediaDTO;
import aptech.vn.backend.entity.MediaType;

import java.util.List;
import java.util.Optional;

public interface MedicineMediaService {
    List<MedicineMediaDTO> findAll();
    Optional<MedicineMediaDTO> findById(Long id);
    MedicineMediaDTO save(MedicineMediaDTO medicineMediaDTO);
    void deleteById(Long id);
    List<MedicineMediaDTO> findByMedicineId(Long medicineId);
    List<MedicineMediaDTO> findByMediaType(MediaType mediaType);
    List<MedicineMediaDTO> findByMedicineIdAndMediaType(Long medicineId, MediaType mediaType);
    Optional<MedicineMediaDTO> findMainImageByMedicineId(Long medicineId);
}