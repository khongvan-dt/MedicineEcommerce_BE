package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.PatientProfile;
import aptech.vn.backend.repository.PatientProfileRepository;
import aptech.vn.backend.service.PatientProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PatientProfileServiceImpl implements PatientProfileService {
    private final PatientProfileRepository patientProfileRepository;

    public PatientProfileServiceImpl(PatientProfileRepository patientProfileRepository) {
        this.patientProfileRepository = patientProfileRepository;
    }

    @Override
    public PatientProfile save(PatientProfile patientProfile) {
        return patientProfileRepository.save(patientProfile);
    }

    @Override
    public Optional<PatientProfile> findById(Long id) {
        return patientProfileRepository.findById(id);
    }

    @Override
    public List<PatientProfile> findAll() {
        return patientProfileRepository.findAll();
    }

    @Override
    public Page<PatientProfile> findAll(Pageable pageable) {
        return patientProfileRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        patientProfileRepository.deleteById(id);
    }

    @Override
    public Optional<PatientProfile> findByUserId(Long userId) {
        return patientProfileRepository.findByUserId(userId);
    }

    @Override
    public List<PatientProfile> findByBloodType(String bloodType) {
        return patientProfileRepository.findByBloodType(bloodType);
    }

    @Override
    public boolean updateAccountBalance(Long patientId, BigDecimal amount) {
        return false;
    }

    @Override
    public List<PatientProfile> findByMedicalHistoryContaining(String keyword) {
        return patientProfileRepository.findByMedicalHistoryContaining(keyword);
    }
}
