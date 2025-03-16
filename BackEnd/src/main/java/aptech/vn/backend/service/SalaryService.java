package aptech.vn.backend.service;

import aptech.vn.backend.dto.SalaryDTO;
import aptech.vn.backend.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SalaryService {
    List<SalaryDTO.GetSalaryDto> findAll();
    Optional<SalaryDTO.GetSalaryDto> findById(Long id);
    SalaryDTO.GetSalaryDto saveOrUpdate(SalaryDTO.SaveSalaryDto salaryDTO);
    void deleteById(Long id);
    List<SalaryDTO.GetSalaryDto> findByUserId(Long userId);
    List<SalaryDTO.GetSalaryDto> findByBankCode(String bankCode);
    List<SalaryDTO.GetSalaryDto> findByBankName(String bankName);
    List<SalaryDTO.GetSalaryDto> findByStatus(PaymentStatus status);
    List<SalaryDTO.GetSalaryDto> findByPriceGreaterThanEqual(BigDecimal amount);
    List<SalaryDTO.GetSalaryDto> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}