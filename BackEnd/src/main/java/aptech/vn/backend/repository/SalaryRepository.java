package aptech.vn.backend.repository;

import aptech.vn.backend.entity.PaymentStatus;
import aptech.vn.backend.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findByUserId(Long userId);
    List<Salary> findByStatus(PaymentStatus status);
    List<Salary> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Salary> findByUserIdAndStatus(Long userId, PaymentStatus status);
    List<Salary> findByBankCode(String bankCode);
}