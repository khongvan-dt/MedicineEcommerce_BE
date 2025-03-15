package aptech.vn.backend.service;

import aptech.vn.backend.dto.PatientProfileDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PatientProfileService {
    List<PatientProfileDTO.GetDto> findAll();
    Optional<PatientProfileDTO.GetDto> findById(Long id);
    PatientProfileDTO.GetDto saveOrUpdate(PatientProfileDTO.SaveDto patientProfileDTO);
    void deleteById(Long id);
    Optional<PatientProfileDTO.GetDto> findByUserId(Long userId);
    List<PatientProfileDTO.GetDto> findByBloodType(String bloodType);
    List<PatientProfileDTO.GetDto> findByMedicalHistoryContaining(String keyword);
    List<PatientProfileDTO.GetDto> findByAllergiesContaining(String keyword);
    List<PatientProfileDTO.GetDto> findByAccountBalanceGreaterThanEqual(BigDecimal amount);
}