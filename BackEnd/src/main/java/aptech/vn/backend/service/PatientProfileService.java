package aptech.vn.backend.service;

import aptech.vn.backend.dto.PatientProfileDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PatientProfileService {
    List<PatientProfileDTO> findAll();
    Optional<PatientProfileDTO> findById(Long id);
    PatientProfileDTO save(PatientProfileDTO patientProfileDTO);
    void deleteById(Long id);
    Optional<PatientProfileDTO> findByUserId(Long userId);
    List<PatientProfileDTO> findByBloodType(String bloodType);
    List<PatientProfileDTO> findByMedicalHistoryContaining(String keyword);
    List<PatientProfileDTO> findByAllergiesContaining(String keyword);
    List<PatientProfileDTO> findByAccountBalanceGreaterThanEqual(BigDecimal amount);
}