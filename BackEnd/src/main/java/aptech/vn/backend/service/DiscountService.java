package aptech.vn.backend.service;

import aptech.vn.backend.dto.DiscountDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiscountService {
    List<DiscountDTO.GetDiscountDto> findAll();
    Optional<DiscountDTO.GetDiscountDto> findById(Long id);
    DiscountDTO.GetDiscountDto saveOrUpdate(DiscountDTO.SaveDiscountDto discountDTO);
    void deleteById(Long id);
    Optional<DiscountDTO.GetDiscountDto> findByCode(String code);
    List<DiscountDTO.GetDiscountDto> findByMedicineId(Long medicineId);
    List<DiscountDTO.GetDiscountDto> findByActive(LocalDateTime now);
    List<DiscountDTO.GetDiscountDto> findByDiscountPercentageGreaterThanEqual(Double percentage);
    List<DiscountDTO.GetDiscountDto> findByExpired(LocalDateTime date);
    List<DiscountDTO.GetDiscountDto> findByNoExpiration();
}