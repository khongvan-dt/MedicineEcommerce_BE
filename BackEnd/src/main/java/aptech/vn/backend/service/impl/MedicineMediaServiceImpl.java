package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.MedicineMediaDTO;
import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.entity.MedicineMedia;
import aptech.vn.backend.entity.MediaType;
import aptech.vn.backend.mapper.MedicineMediaMapper;
import aptech.vn.backend.repository.MedicineMediaRepository;
import aptech.vn.backend.repository.MedicineRepository;
import aptech.vn.backend.service.MedicineMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineMediaServiceImpl implements MedicineMediaService {

    private final MedicineMediaRepository medicineMediaRepository;
    private final MedicineRepository medicineRepository;
    private final MedicineMediaMapper medicineMediaMapper;

    @Autowired
    public MedicineMediaServiceImpl(
            MedicineMediaRepository medicineMediaRepository,
            MedicineRepository medicineRepository,
            MedicineMediaMapper medicineMediaMapper) {
        this.medicineMediaRepository = medicineMediaRepository;
        this.medicineRepository = medicineRepository;
        this.medicineMediaMapper = medicineMediaMapper;
    }

    @Override
    public List<MedicineMediaDTO.GetDto> findAll() {
        return medicineMediaRepository.findAll().stream()
                .map(medicineMediaMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicineMediaDTO.GetDto> findById(Long id) {
        return medicineMediaRepository.findById(id)
                .map(medicineMediaMapper::toGetDto);
    }

    @Override
    @Transactional
    public MedicineMediaDTO.GetDto saveOrUpdate(MedicineMediaDTO.SaveDto medicineMediaDTO) {
        MedicineMedia medicineMedia;

        if (medicineMediaDTO.getId() == null || medicineMediaDTO.getId() == 0) {
            // INSERT case
            medicineMedia = new MedicineMedia();
            medicineMedia.setCreatedAt(LocalDateTime.now());
            medicineMedia.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<MedicineMedia> existingMedia = medicineMediaRepository.findById(medicineMediaDTO.getId());
            if (existingMedia.isEmpty()) {
                throw new RuntimeException("Medicine media not found with ID: " + medicineMediaDTO.getId());
            }
            medicineMedia = existingMedia.get();
            medicineMedia.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý medicine relationship
        Medicine medicine = medicineRepository.findById(medicineMediaDTO.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found with ID: " + medicineMediaDTO.getMedicineId()));
        medicineMedia.setMedicine(medicine);

        // Cập nhật các trường khác
        medicineMedia.setMediaType(medicineMediaDTO.getMediaType());
        medicineMedia.setMediaUrl(medicineMediaDTO.getMediaUrl());
        medicineMedia.setMainImage(medicineMediaDTO.getMainImage());

        // Nếu đánh dấu là ảnh chính, hãy tắt các ảnh chính khác của cùng thuốc
        if (Boolean.TRUE.equals(medicineMediaDTO.getMainImage())) {
            medicineMediaRepository.findByMedicine_IdAndMainImageTrue(medicineMediaDTO.getMedicineId())
                    .ifPresent(existingMainImage -> {
                        if (!existingMainImage.getId().equals(medicineMediaDTO.getId())) {
                            existingMainImage.setMainImage(false);
                            medicineMediaRepository.save(existingMainImage);
                        }
                    });
        }

        MedicineMedia savedMedia = medicineMediaRepository.save(medicineMedia);
        return medicineMediaMapper.toGetDto(savedMedia);
    }

    @Override
    public void deleteById(Long id) {
        medicineMediaRepository.deleteById(id);
    }

    @Override
    public List<MedicineMediaDTO.GetDto> findByMedicineId(Long medicineId) {
        return medicineMediaRepository.findByMedicine_Id(medicineId).stream()
                .map(medicineMediaMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineMediaDTO.GetDto> findByMediaType(MediaType mediaType) {
        return medicineMediaRepository.findByMediaType(mediaType).stream()
                .map(medicineMediaMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineMediaDTO.GetDto> findByMedicineIdAndMediaType(Long medicineId, MediaType mediaType) {
        return medicineMediaRepository.findByMedicine_IdAndMediaType(medicineId, mediaType).stream()
                .map(medicineMediaMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicineMediaDTO.GetDto> findMainImageByMedicineId(Long medicineId) {
        return medicineMediaRepository.findByMedicine_IdAndMainImageTrue(medicineId)
                .map(medicineMediaMapper::toGetDto);
    }
}