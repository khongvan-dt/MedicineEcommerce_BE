package aptech.vn.backend.repository;

import aptech.vn.backend.entity.MedicineCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineCategoryRepository extends JpaRepository<MedicineCategory, Long> {
    List<MedicineCategory> findByMedicineId(Long medicineId);
    List<MedicineCategory> findByCategoryId(Long categoryId);
    Optional<MedicineCategory> findByMedicineIdAndCategoryId(Long medicineId, Long categoryId);
}