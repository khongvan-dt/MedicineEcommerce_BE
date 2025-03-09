package aptech.vn.backend.repository;

import aptech.vn.backend.entity.MedicineCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineCategoryRepository extends JpaRepository<MedicineCategory, Long> {
    List<MedicineCategory> findByMedicine_Id(Long medicineId);
    List<MedicineCategory> findByCategory_Id(Long categoryId);
    Optional<MedicineCategory> findByMedicine_IdAndCategory_Id(Long medicineId, Long categoryId);
}