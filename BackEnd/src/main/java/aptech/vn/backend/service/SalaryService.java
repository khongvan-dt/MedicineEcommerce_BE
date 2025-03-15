package aptech.vn.backend.service;

import aptech.vn.backend.dto.SalaryDTO;
import aptech.vn.backend.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SalaryService {
    List<SalaryDTO.GetDto> findAll();
    Optional<SalaryDTO.GetDto> findById(Long id);
    SalaryDTO.GetDto saveOrUpdate(SalaryDTO.SaveDto salaryDTO);
    void deleteById(Long id);
    List<SalaryDTO.GetDto> findByUserId(Long userId);
    List<SalaryDTO.GetDto> findByBankCode(String bankCode);
    List<SalaryDTO.GetDto> findByBankName(String bankName);
    List<SalaryDTO.GetDto> findByStatus(PaymentStatus status);
    List<SalaryDTO.GetDto> findByPriceGreaterThanEqual(BigDecimal amount);
    List<SalaryDTO.GetDto> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}