package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineBatchDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicineBatchService {
    List<MedicineBatchDTO.GetDto> findAll();
    Optional<MedicineBatchDTO.GetDto> findById(Long id);
    MedicineBatchDTO.GetDto saveOrUpdate(MedicineBatchDTO.SaveDto medicineBatchDTO);
    void deleteById(Long id);
    Optional<MedicineBatchDTO.GetDto> findByBatchName(String batchName);
    List<MedicineBatchDTO.GetDto> findByMedicineCode(String medicineCode);
    List<MedicineBatchDTO.GetDto> findByQuantityGreaterThan(Integer quantity);
    List<MedicineBatchDTO.GetDto> findByExpiryDateBefore(LocalDate date);
}