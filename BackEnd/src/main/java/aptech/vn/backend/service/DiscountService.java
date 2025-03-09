package aptech.vn.backend.service;

import aptech.vn.backend.dto.DiscountDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiscountService {
    List<DiscountDTO> findAll();
    Optional<DiscountDTO> findById(Long id);
    DiscountDTO save(DiscountDTO discountDTO);
    void deleteById(Long id);
    Optional<DiscountDTO> findByCode(String code);
    List<DiscountDTO> findByMedicineId(Long medicineId);
    List<DiscountDTO> findByActive(LocalDateTime now);
    List<DiscountDTO> findByDiscountPercentageGreaterThanEqual(Double percentage);
    List<DiscountDTO> findByExpired(LocalDateTime date);
    List<DiscountDTO> findByNoExpiration();
}