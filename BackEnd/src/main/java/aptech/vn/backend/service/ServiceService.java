package aptech.vn.backend.service;

import aptech.vn.backend.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ServiceService {
    Service save(Service service);
    Optional<Service> findById(Long id);
    List<Service> findAll();
    Page<Service> findAll(Pageable pageable);
    void deleteById(Long id);
    List<Service> findByNameContaining(String keyword);
    List<Service> findByPriceLessThan(BigDecimal maxPrice);
    List<Service> findByPriceGreaterThan(BigDecimal minPrice);
    List<Service> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}