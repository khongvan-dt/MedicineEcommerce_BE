package aptech.vn.backend.repository;

import aptech.vn.backend.entity.MedicineMedia;
import aptech.vn.backend.entity.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineMediaRepository extends JpaRepository<MedicineMedia, Long> {
    List<MedicineMedia> findByMedicine_Id(Long medicineId);
    List<MedicineMedia> findByMediaType(MediaType mediaType);
    List<MedicineMedia> findByMedicine_IdAndMediaType(Long medicineId, MediaType mediaType);
    Optional<MedicineMedia> findByMedicine_IdAndMainImageTrue(Long medicineId);
}