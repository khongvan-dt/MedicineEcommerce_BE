package aptech.vn.backend.service;

import aptech.vn.backend.entity.Salary;
import aptech.vn.backend.entity.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SalaryService {
    Salary save(Salary salary);
    Optional<Salary> findById(Long id);
    List<Salary> findAll();
    Page<Salary> findAll(Pageable pageable);
    void deleteById(Long id);
    List<Salary> findByUserId(Long userId);
    List<Salary> findByStatus(PaymentStatus status);
    List<Salary> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    BigDecimal getTotalSalaryByUserIdAndPeriod(Long userId, LocalDateTime start, LocalDateTime end);
    boolean updateStatus(Long salaryId, PaymentStatus newStatus);
}
