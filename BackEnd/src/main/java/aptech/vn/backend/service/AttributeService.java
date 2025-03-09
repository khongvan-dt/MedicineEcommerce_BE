package aptech.vn.backend.service;

import aptech.vn.backend.dto.AttributeDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttributeService {
    List<AttributeDTO> findAll();
    Optional<AttributeDTO> findById(Long id);
    AttributeDTO save(AttributeDTO attributeDTO);
    void deleteById(Long id);
    List<AttributeDTO> findByName(String name);
    List<AttributeDTO> findByPriceInLessThanEqual(BigDecimal price);
    List<AttributeDTO> findByPriceOutLessThanEqual(BigDecimal price);
    List<AttributeDTO> findByStockGreaterThan(Integer stock);
    List<AttributeDTO> findByExpiryDateBefore(LocalDate date);
}
