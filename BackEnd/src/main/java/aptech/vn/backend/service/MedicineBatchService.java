package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineBatchDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicineBatchService {
    List<MedicineBatchDTO> findAll();
    Optional<MedicineBatchDTO> findById(Long id);
    MedicineBatchDTO save(MedicineBatchDTO medicineBatchDTO);
    void deleteById(Long id);
    Optional<MedicineBatchDTO> findByBatchName(String batchName);
    List<MedicineBatchDTO> findByMedicineCode(String medicineCode);
    List<MedicineBatchDTO> findByQuantityGreaterThan(Integer quantity);
    List<MedicineBatchDTO> findByExpiryDateBefore(LocalDate date);
}