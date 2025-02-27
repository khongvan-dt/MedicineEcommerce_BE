
package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    List<Attribute> findByName(String name);
    List<Attribute> findByExpiryDateBefore(LocalDate date);
    List<Attribute> findByStockLessThan(Integer minStock);
}