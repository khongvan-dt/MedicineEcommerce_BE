package aptech.vn.backend.service;

import aptech.vn.backend.dto.PatientProfileDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PatientProfileService {
    List<PatientProfileDTO.GetPatientProfileDto> findAll();
    Optional<PatientProfileDTO.GetPatientProfileDto> findById(Long id);
    PatientProfileDTO.GetPatientProfileDto saveOrUpdate(PatientProfileDTO.SavePatientProfileDto patientProfileDTO);
    void deleteById(Long id);
    Optional<PatientProfileDTO.GetPatientProfileDto> findByUserId(Long userId);
    List<PatientProfileDTO.GetPatientProfileDto> findByBloodType(String bloodType);
    List<PatientProfileDTO.GetPatientProfileDto> findByMedicalHistoryContaining(String keyword);
    List<PatientProfileDTO.GetPatientProfileDto> findByAllergiesContaining(String keyword);
    List<PatientProfileDTO.GetPatientProfileDto> findByAccountBalanceGreaterThanEqual(BigDecimal amount);
}