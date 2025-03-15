package aptech.vn.backend.service;

import aptech.vn.backend.dto.DiscountDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiscountService {
    List<DiscountDTO.GetDto> findAll();
    Optional<DiscountDTO.GetDto> findById(Long id);
    DiscountDTO.GetDto saveOrUpdate(DiscountDTO.SaveDto discountDTO);
    void deleteById(Long id);
    Optional<DiscountDTO.GetDto> findByCode(String code);
    List<DiscountDTO.GetDto> findByMedicineId(Long medicineId);
    List<DiscountDTO.GetDto> findByActive(LocalDateTime now);
    List<DiscountDTO.GetDto> findByDiscountPercentageGreaterThanEqual(Double percentage);
    List<DiscountDTO.GetDto> findByExpired(LocalDateTime date);
    List<DiscountDTO.GetDto> findByNoExpiration();
}