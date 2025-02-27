package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.MedicineBatch;
import aptech.vn.backend.repository.MedicineBatchRepository;
import aptech.vn.backend.service.MedicineBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicineBatchServiceImpl implements MedicineBatchService {

    private final MedicineBatchRepository medicineBatchRepository;

    @Autowired
    public MedicineBatchServiceImpl(MedicineBatchRepository medicineBatchRepository) {
        this.medicineBatchRepository = medicineBatchRepository;
    }

    @Override
    public MedicineBatch save(MedicineBatch medicineBatch) {
        return medicineBatchRepository.save(medicineBatch);
    }

    @Override
    public List<MedicineBatch> findAll() {
        return medicineBatchRepository.findAll();
    }

    @Override
    public Optional<MedicineBatch> findById(Long id) {
        return medicineBatchRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        medicineBatchRepository.deleteById(id);
    }

    @Override
    public Optional<MedicineBatch> findByBatchName(String batchName) {
        return medicineBatchRepository.findByBatchName(batchName);
    }

    @Override
    public List<MedicineBatch> findByMedicineCode(String medicineCode) {
        return medicineBatchRepository.findByMedicineCode(medicineCode);
    }

    @Override
    public List<MedicineBatch> findByExpiryDateBefore(LocalDate date) {
        return medicineBatchRepository.findByExpiryDateBefore(date);
    }

    @Override
    public List<MedicineBatch> findByQuantityLessThan(Integer minQuantity) {
        return medicineBatchRepository.findByQuantityLessThan(minQuantity);
    }

    @Override
    public List<MedicineBatch> findExpiredBatches() {
        return medicineBatchRepository.findByExpiryDateBefore(LocalDate.now());
    }

    @Override
    public List<MedicineBatch> findLowStockBatches(Integer threshold) {
        return medicineBatchRepository.findByQuantityLessThan(threshold);
    }
}