package aptech.vn.backend.service;

import aptech.vn.backend.entity.MedicineMedia;
import aptech.vn.backend.entity.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MedicineMediaService {
    MedicineMedia save(MedicineMedia medicineMedia);
    Optional<MedicineMedia> findById(Long id);
    List<MedicineMedia> findAll();
    Page<MedicineMedia> findAll(Pageable pageable);
    void deleteById(Long id);
    List<MedicineMedia> findByMedicineId(Long medicineId);
    List<MedicineMedia> findByMedicineIdAndMediaType(Long medicineId, MediaType mediaType);
    Optional<MedicineMedia> findMainImageByMedicineId(Long medicineId);
    void setMainImage(Long medicineId, Long mediaId);
}