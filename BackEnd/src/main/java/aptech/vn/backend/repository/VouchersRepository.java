package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Vouchers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VouchersRepository extends JpaRepository<Vouchers, Long> {
    Optional<Vouchers> findByCode(String code);
    List<Vouchers> findByStockGreaterThan(Integer minStock);
    List<Vouchers> findByVoucherPercentageGreaterThanEqual(Double percentage);
    List<Vouchers> findByStartDateBeforeAndEndDateAfter(LocalDateTime now, LocalDateTime now2);
    List<Vouchers> findByEndDateBefore(LocalDateTime date);
    List<Vouchers> findByEndDateIsNull();
}