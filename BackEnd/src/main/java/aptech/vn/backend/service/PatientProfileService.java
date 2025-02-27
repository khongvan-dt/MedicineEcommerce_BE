package aptech.vn.backend.service;

import aptech.vn.backend.entity.PatientProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PatientProfileService {
    PatientProfile save(PatientProfile patientProfile);
    Optional<PatientProfile> findById(Long id);
    List<PatientProfile> findAll();
    Page<PatientProfile> findAll(Pageable pageable);
    void deleteById(Long id);
    Optional<PatientProfile> findByUserId(Long userId);
    List<PatientProfile> findByBloodType(String bloodType);
    boolean updateAccountBalance(Long patientId, BigDecimal amount);
    List<PatientProfile> findByMedicalHistoryContaining(String keyword);
}