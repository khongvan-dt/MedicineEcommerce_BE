package aptech.vn.backend.repository;

import aptech.vn.backend.entity.MedicineBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineBatchRepository extends JpaRepository<MedicineBatch, Long> {
    Optional<MedicineBatch> findByBatchName(String batchName);
    List<MedicineBatch> findByMedicineCode(String medicineCode);
    List<MedicineBatch> findByExpiryDateBefore(LocalDate date);
    List<MedicineBatch> findByQuantityLessThan(Integer minQuantity);
}