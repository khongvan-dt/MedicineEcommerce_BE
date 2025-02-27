package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.MedicineMedia;
import aptech.vn.backend.entity.MediaType;
import aptech.vn.backend.repository.MedicineMediaRepository;
import aptech.vn.backend.service.MedicineMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicineMediaServiceImpl implements MedicineMediaService {

    private final MedicineMediaRepository medicineMediaRepository;

    @Autowired
    public MedicineMediaServiceImpl(MedicineMediaRepository medicineMediaRepository) {
        this.medicineMediaRepository = medicineMediaRepository;
    }

    @Override
    public MedicineMedia save(MedicineMedia medicineMedia) {
        return medicineMediaRepository.save(medicineMedia);
    }

    @Override
    public Optional<MedicineMedia> findById(Long id) {
        return medicineMediaRepository.findById(id);
    }

    @Override
    public List<MedicineMedia> findAll() {
        return medicineMediaRepository.findAll();
    }

    @Override
    public Page<MedicineMedia> findAll(Pageable pageable) {
        return medicineMediaRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        medicineMediaRepository.deleteById(id);
    }

    @Override
    public List<MedicineMedia> findByMedicineId(Long medicineId) {
        return medicineMediaRepository.findByMedicineId(medicineId);
    }

    @Override
    public List<MedicineMedia> findByMedicineIdAndMediaType(Long medicineId, MediaType mediaType) {
        return medicineMediaRepository.findByMedicineIdAndMediaType(medicineId, mediaType);
    }

    @Override
    public Optional<MedicineMedia> findMainImageByMedicineId(Long medicineId) {
        return medicineMediaRepository.findByMedicineIdAndMainImageTrue(medicineId);
    }

    @Override
    public void setMainImage(Long medicineId, Long mediaId) {
        return;
    }
}