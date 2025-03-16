package aptech.vn.backend.service;

import aptech.vn.backend.dto.AttributeDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttributeService {
    List<AttributeDTO.GetAttributeDto> findAll();
    Optional<AttributeDTO.GetAttributeDto> findById(Long id);
    AttributeDTO.GetAttributeDto saveOrUpdate(AttributeDTO.SaveAttributeDto attributeDTO);
    void deleteById(Long id);
    List<AttributeDTO.GetAttributeDto> findByName(String name);
    List<AttributeDTO.GetAttributeDto> findByPriceInLessThanEqual(BigDecimal price);
    List<AttributeDTO.GetAttributeDto> findByPriceOutLessThanEqual(BigDecimal price);
    List<AttributeDTO.GetAttributeDto> findByStockGreaterThan(Integer stock);
    List<AttributeDTO.GetAttributeDto> findByExpiryDateBefore(LocalDate date);
}