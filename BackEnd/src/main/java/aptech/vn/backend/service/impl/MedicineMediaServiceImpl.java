package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.MedicineMediaDTO;
import aptech.vn.backend.entity.MedicineMedia;
import aptech.vn.backend.entity.MediaType;
import aptech.vn.backend.mapper.MedicineMediaMapper;
import aptech.vn.backend.repository.MedicineMediaRepository;
import aptech.vn.backend.service.MedicineMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineMediaServiceImpl implements MedicineMediaService {

    private final MedicineMediaRepository medicineMediaRepository;
    private final MedicineMediaMapper medicineMediaMapper;

    @Autowired
    public MedicineMediaServiceImpl(MedicineMediaRepository medicineMediaRepository, MedicineMediaMapper medicineMediaMapper) {
        this.medicineMediaRepository = medicineMediaRepository;
        this.medicineMediaMapper = medicineMediaMapper;
    }

    public List<MedicineMediaDTO> findAll() {
        return medicineMediaRepository.findAll().stream()
                .map(medicineMediaMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MedicineMediaDTO> findById(Long id) {
        return medicineMediaRepository.findById(id)
                .map(medicineMediaMapper::toDto);
    }

    public MedicineMediaDTO save(MedicineMediaDTO medicineMediaDTO) {
        MedicineMedia medicineMedia = medicineMediaMapper.toEntity(medicineMediaDTO);
        MedicineMedia savedMedicineMedia = medicineMediaRepository.save(medicineMedia);
        return medicineMediaMapper.toDto(savedMedicineMedia);
    }

    public void deleteById(Long id) {
        medicineMediaRepository.deleteById(id);
    }

    public List<MedicineMediaDTO> findByMedicineId(Long medicineId) {
        return medicineMediaRepository.findByMedicine_Id(medicineId).stream()
                .map(medicineMediaMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MedicineMediaDTO> findByMediaType(MediaType mediaType) {
        return medicineMediaRepository.findByMediaType(mediaType).stream()
                .map(medicineMediaMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MedicineMediaDTO> findByMedicineIdAndMediaType(Long medicineId, MediaType mediaType) {
        return medicineMediaRepository.findByMedicine_IdAndMediaType(medicineId, mediaType).stream()
                .map(medicineMediaMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MedicineMediaDTO> findMainImageByMedicineId(Long medicineId) {
        return medicineMediaRepository.findByMedicine_IdAndMainImageTrue(medicineId)
                .map(medicineMediaMapper::toDto);
    }
}