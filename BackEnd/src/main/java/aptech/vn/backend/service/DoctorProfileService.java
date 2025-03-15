package aptech.vn.backend.service;

import aptech.vn.backend.dto.DoctorProfileDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DoctorProfileService {
    List<DoctorProfileDTO.GetDto> findAll();
    Optional<DoctorProfileDTO.GetDto> findById(Long id);
    DoctorProfileDTO.GetDto saveOrUpdate(DoctorProfileDTO.SaveDto doctorProfileDTO);
    void deleteById(Long id);
    Optional<DoctorProfileDTO.GetDto> findByUserId(Long userId);
    List<DoctorProfileDTO.GetDto> findBySpecializationContaining(String specialization);
    List<DoctorProfileDTO.GetDto> findByWorkplaceContaining(String workplace);
    List<DoctorProfileDTO.GetDto> findByAccountBalanceGreaterThanEqual(BigDecimal amount);
}