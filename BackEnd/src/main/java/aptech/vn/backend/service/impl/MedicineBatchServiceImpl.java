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

    public List<MedicineBatchDTO> findAll() {
        return medicineBatchRepository.findAll().stream()
                .map(medicineBatchMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MedicineBatchDTO> findById(Long id) {
        return medicineBatchRepository.findById(id)
                .map(medicineBatchMapper::toDto);
    }

    public MedicineBatchDTO save(MedicineBatchDTO medicineBatchDTO) {
        MedicineBatch medicineBatch = medicineBatchMapper.toEntity(medicineBatchDTO);
        MedicineBatch savedMedicineBatch = medicineBatchRepository.save(medicineBatch);
        return medicineBatchMapper.toDto(savedMedicineBatch);
    }

    public void deleteById(Long id) {
        medicineBatchRepository.deleteById(id);
    }

    public Optional<MedicineBatchDTO> findByBatchName(String batchName) {
        return medicineBatchRepository.findByBatchName(batchName)
                .map(medicineBatchMapper::toDto);
    }

    public List<MedicineBatchDTO> findByMedicineCode(String medicineCode) {
        return medicineBatchRepository.findByMedicineCode(medicineCode).stream()
                .map(medicineBatchMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MedicineBatchDTO> findByQuantityGreaterThan(Integer quantity) {
        return medicineBatchRepository.findByQuantityGreaterThan(quantity).stream()
                .map(medicineBatchMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MedicineBatchDTO> findByExpiryDateBefore(LocalDate date) {
        return medicineBatchRepository.findByExpiryDateBefore(date).stream()
                .map(medicineBatchMapper::toDto)
                .collect(Collectors.toList());
    }
}