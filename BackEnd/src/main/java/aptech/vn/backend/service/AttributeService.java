package aptech.vn.backend.service;

import aptech.vn.backend.dto.AttributeDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttributeService {
    List<AttributeDTO.GetDto> findAll();
    Optional<AttributeDTO.GetDto> findById(Long id);
    AttributeDTO.GetDto saveOrUpdate(AttributeDTO.SaveDto attributeDTO);
    void deleteById(Long id);
    List<AttributeDTO.GetDto> findByName(String name);
    List<AttributeDTO.GetDto> findByPriceInLessThanEqual(BigDecimal price);
    List<AttributeDTO.GetDto> findByPriceOutLessThanEqual(BigDecimal price);
    List<AttributeDTO.GetDto> findByStockGreaterThan(Integer stock);
    List<AttributeDTO.GetDto> findByExpiryDateBefore(LocalDate date);
}