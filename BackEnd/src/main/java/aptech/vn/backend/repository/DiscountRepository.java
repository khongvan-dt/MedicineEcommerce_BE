package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Optional<Discount> findByCode(String code);
    List<Discount> findByMedicine_Id(Long medicineId);
    List<Discount> findByStartDateBeforeAndEndDateAfter(LocalDateTime now, LocalDateTime now2);
    List<Discount> findByDiscountPercentageGreaterThanEqual(Double percentage);
    List<Discount> findByEndDateBefore(LocalDateTime date);
    List<Discount> findByEndDateIsNull();
}