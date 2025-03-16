package aptech.vn.backend.service;

import aptech.vn.backend.dto.DoctorProfileDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DoctorProfileService {
    List<DoctorProfileDTO.GetDoctorProfileDto> findAll();
    Optional<DoctorProfileDTO.GetDoctorProfileDto> findById(Long id);
    DoctorProfileDTO.GetDoctorProfileDto saveOrUpdate(DoctorProfileDTO.SaveDoctorProfileDto doctorProfileDTO);
    void deleteById(Long id);
    Optional<DoctorProfileDTO.GetDoctorProfileDto> findByUserId(Long userId);
    List<DoctorProfileDTO.GetDoctorProfileDto> findBySpecializationContaining(String specialization);
    List<DoctorProfileDTO.GetDoctorProfileDto> findByWorkplaceContaining(String workplace);
    List<DoctorProfileDTO.GetDoctorProfileDto> findByAccountBalanceGreaterThanEqual(BigDecimal amount);
}