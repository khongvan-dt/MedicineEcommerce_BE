package aptech.vn.backend.service;

import aptech.vn.backend.dto.ServiceDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<ServiceDTO> findAll();
    Optional<ServiceDTO> findById(Long id);
    ServiceDTO save(ServiceDTO serviceDTO);
    void deleteById(Long id);
    List<ServiceDTO> findByName(String name);
    List<ServiceDTO> findByNameContaining(String namePattern);
    List<ServiceDTO> findByPriceLessThanEqual(BigDecimal maxPrice);
    List<ServiceDTO> findByPriceGreaterThanEqual(BigDecimal minPrice);
    List<ServiceDTO> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}