package aptech.vn.backend.service;

import aptech.vn.backend.dto.MedicineBatchDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicineBatchService {
    List<MedicineBatchDTO.GetMedicineBatchDto> findAll();
    Optional<MedicineBatchDTO.GetMedicineBatchDto> findById(Long id);
    MedicineBatchDTO.GetMedicineBatchDto saveOrUpdate(MedicineBatchDTO.SaveMedicineBatchDto medicineBatchDTO);
    void deleteById(Long id);
    Optional<MedicineBatchDTO.GetMedicineBatchDto> findByBatchName(String batchName);
    List<MedicineBatchDTO.GetMedicineBatchDto> findByMedicineCode(String medicineCode);
    List<MedicineBatchDTO.GetMedicineBatchDto> findByQuantityGreaterThan(Integer quantity);
    List<MedicineBatchDTO.GetMedicineBatchDto> findByExpiryDateBefore(LocalDate date);
}