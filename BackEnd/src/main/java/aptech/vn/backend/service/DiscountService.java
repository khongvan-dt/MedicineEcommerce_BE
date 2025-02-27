package aptech.vn.backend.service;

import aptech.vn.backend.entity.Discount;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiscountService {
    Discount save(Discount discount);
    List<Discount> findAll();
    Optional<Discount> findById(Long id);
    void deleteById(Long id);
    Optional<Discount> findByCode(String code);
    List<Discount> findByMedicineId(Long medicineId);
    List<Discount> findByStartDateBeforeAndEndDateAfter(LocalDateTime now, LocalDateTime now2);
    List<Discount> findByEndDateBefore(LocalDateTime now);
    List<Discount> findActiveDiscounts();
    List<Discount> findExpiredDiscounts();
}