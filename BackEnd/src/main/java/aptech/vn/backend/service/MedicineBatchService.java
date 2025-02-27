package aptech.vn.backend.service;

import aptech.vn.backend.entity.MedicineBatch;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicineBatchService {
    MedicineBatch save(MedicineBatch medicineBatch);
    List<MedicineBatch> findAll();
    Optional<MedicineBatch> findById(Long id);
    void deleteById(Long id);
    Optional<MedicineBatch> findByBatchName(String batchName);
    List<MedicineBatch> findByMedicineCode(String medicineCode);
    List<MedicineBatch> findByExpiryDateBefore(LocalDate date);
    List<MedicineBatch> findByQuantityLessThan(Integer minQuantity);
    List<MedicineBatch> findExpiredBatches();
    List<MedicineBatch> findLowStockBatches(Integer threshold);
}