package aptech.vn.backend.repository;

import aptech.vn.backend.entity.PaymentStatus;
import aptech.vn.backend.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findByUser_Id(Long userId);
    List<Salary> findByBankCode(String bankCode);
    List<Salary> findByBankName(String bankName);
    List<Salary> findByStatus(PaymentStatus status);
    List<Salary> findByPriceGreaterThanEqual(BigDecimal amount);
    List<Salary> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}