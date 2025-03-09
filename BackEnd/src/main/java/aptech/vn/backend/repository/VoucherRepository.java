package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Optional<Voucher> findByCode(String code);
    List<Voucher> findByStockGreaterThan(Integer minStock);
    List<Voucher> findByVoucherPercentageGreaterThanEqual(Double percentage);
    List<Voucher> findByStartDateBeforeAndEndDateAfter(LocalDateTime now, LocalDateTime now2);
    List<Voucher> findByEndDateBefore(LocalDateTime date);
    List<Voucher> findByEndDateIsNull();
}