package aptech.vn.backend.service;

import aptech.vn.backend.dto.DoctorProfileDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DoctorProfileService {
    List<DoctorProfileDTO> findAll();
    Optional<DoctorProfileDTO> findById(Long id);
    DoctorProfileDTO save(DoctorProfileDTO doctorProfileDTO);
    void deleteById(Long id);
    Optional<DoctorProfileDTO> findByUserId(Long userId);
    List<DoctorProfileDTO> findBySpecializationContaining(String specialization);
    List<DoctorProfileDTO> findByWorkplaceContaining(String workplace);
    List<DoctorProfileDTO> findByAccountBalanceGreaterThanEqual(BigDecimal amount);
}