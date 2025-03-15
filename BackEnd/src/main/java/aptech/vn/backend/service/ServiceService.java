package aptech.vn.backend.service;

import aptech.vn.backend.dto.ServiceDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<ServiceDTO.GetDto> findAll();
    Optional<ServiceDTO.GetDto> findById(Long id);
    ServiceDTO.GetDto saveOrUpdate(ServiceDTO.SaveDto serviceDTO);
    void deleteById(Long id);
    List<ServiceDTO.GetDto> findByName(String name);
    List<ServiceDTO.GetDto> findByNameContaining(String namePattern);
    List<ServiceDTO.GetDto> findByPriceLessThanEqual(BigDecimal maxPrice);
    List<ServiceDTO.GetDto> findByPriceGreaterThanEqual(BigDecimal minPrice);
    List<ServiceDTO.GetDto> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}