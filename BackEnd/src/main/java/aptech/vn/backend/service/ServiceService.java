package aptech.vn.backend.service;

import aptech.vn.backend.dto.ServiceDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<ServiceDTO.GetServiceDto> findAll();
    Optional<ServiceDTO.GetServiceDto> findById(Long id);
    ServiceDTO.GetServiceDto saveOrUpdate(ServiceDTO.SaveServiceDto serviceDTO);
    void deleteById(Long id);
    List<ServiceDTO.GetServiceDto> findByName(String name);
    List<ServiceDTO.GetServiceDto> findByNameContaining(String namePattern);
    List<ServiceDTO.GetServiceDto> findByPriceLessThanEqual(BigDecimal maxPrice);
    List<ServiceDTO.GetServiceDto> findByPriceGreaterThanEqual(BigDecimal minPrice);
    List<ServiceDTO.GetServiceDto> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}