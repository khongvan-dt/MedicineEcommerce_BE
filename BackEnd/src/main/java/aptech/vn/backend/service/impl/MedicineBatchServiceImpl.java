package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.MedicineBatchDTO;
import aptech.vn.backend.entity.MedicineBatch;
import aptech.vn.backend.mapper.MedicineBatchMapper;
import aptech.vn.backend.repository.MedicineBatchRepository;
import aptech.vn.backend.service.MedicineBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineBatchServiceImpl implements MedicineBatchService {

    private final MedicineBatchRepository medicineBatchRepository;
    private final MedicineBatchMapper medicineBatchMapper;

    @Autowired
    public MedicineBatchServiceImpl(MedicineBatchRepository medicineBatchRepository, MedicineBatchMapper medicineBatchMapper) {
        this.medicineBatchRepository = medicineBatchRepository;
        this.medicineBatchMapper = medicineBatchMapper;
    }

    @Override
    public List<MedicineBatchDTO.GetMedicineBatchDto> findAll() {
        return medicineBatchRepository.findAll().stream()
                .map(medicineBatchMapper::toGetMedicineBatchDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicineBatchDTO.GetMedicineBatchDto> findById(Long id) {
        return medicineBatchRepository.findById(id)
                .map(medicineBatchMapper::toGetMedicineBatchDto);
    }

    @Override
    @Transactional
    public MedicineBatchDTO.GetMedicineBatchDto saveOrUpdate(MedicineBatchDTO.SaveMedicineBatchDto medicineBatchDTO) {
        MedicineBatch medicineBatch;

        if (medicineBatchDTO.getId() == null || medicineBatchDTO.getId() == 0) {
            // INSERT case
            medicineBatch = new MedicineBatch();
            medicineBatch.setCreatedAt(LocalDateTime.now());
            medicineBatch.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<MedicineBatch> existingMedicineBatch = medicineBatchRepository.findById(medicineBatchDTO.getId());
            if (existingMedicineBatch.isEmpty()) {
                throw new RuntimeException("Medicine batch not found with ID: " + medicineBatchDTO.getId());
            }
            medicineBatch = existingMedicineBatch.get();
            medicineBatch.setUpdatedAt(LocalDateTime.now());
        }

        // Cập nhật các trường
        medicineBatch.setBatchName(medicineBatchDTO.getBatchName());
        medicineBatch.setMedicineCode(medicineBatchDTO.getMedicineCode());
        medicineBatch.setQuantity(medicineBatchDTO.getQuantity());
        medicineBatch.setExpiryDate(medicineBatchDTO.getExpiryDate());

        MedicineBatch savedMedicineBatch = medicineBatchRepository.save(medicineBatch);
        return medicineBatchMapper.toGetMedicineBatchDto(savedMedicineBatch);
    }

    @Override
    public void deleteById(Long id) {
        medicineBatchRepository.deleteById(id);
    }

    @Override
    public Optional<MedicineBatchDTO.GetMedicineBatchDto> findByBatchName(String batchName) {
        return medicineBatchRepository.findByBatchName(batchName)
                .map(medicineBatchMapper::toGetMedicineBatchDto);
    }

    @Override
    public List<MedicineBatchDTO.GetMedicineBatchDto> findByMedicineCode(String medicineCode) {
        return medicineBatchRepository.findByMedicineCode(medicineCode).stream()
                .map(medicineBatchMapper::toGetMedicineBatchDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineBatchDTO.GetMedicineBatchDto> findByQuantityGreaterThan(Integer quantity) {
        return medicineBatchRepository.findByQuantityGreaterThan(quantity).stream()
                .map(medicineBatchMapper::toGetMedicineBatchDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineBatchDTO.GetMedicineBatchDto> findByExpiryDateBefore(LocalDate date) {
        return medicineBatchRepository.findByExpiryDateBefore(date).stream()
                .map(medicineBatchMapper::toGetMedicineBatchDto)
                .collect(Collectors.toList());
    }
}