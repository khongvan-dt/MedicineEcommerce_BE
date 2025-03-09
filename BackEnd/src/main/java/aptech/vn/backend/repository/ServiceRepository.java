package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findByName(String name);
    List<Service> findByNameContaining(String namePattern);
    List<Service> findByPriceLessThanEqual(BigDecimal maxPrice);
    List<Service> findByPriceGreaterThanEqual(BigDecimal minPrice);
    List<Service> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}