package aptech.vn.backend.service;

import aptech.vn.backend.dto.SalaryDTO;
import aptech.vn.backend.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface SalaryService {
    List<SalaryDTO> findAll();
    Optional<SalaryDTO> findById(Long id);
    SalaryDTO save(SalaryDTO salaryDTO);
    void deleteById(Long id);
    List<SalaryDTO> findByUserId(Long userId);
    List<SalaryDTO> findByBankCode(String bankCode);
    List<SalaryDTO> findByBankName(String bankName);
    List<SalaryDTO> findByStatus(PaymentStatus status);
    List<SalaryDTO> findByPriceGreaterThanEqual(BigDecimal amount);
    List<SalaryDTO> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}