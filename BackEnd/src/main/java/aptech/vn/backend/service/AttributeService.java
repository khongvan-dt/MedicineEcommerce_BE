package aptech.vn.backend.service;

import aptech.vn.backend.entity.Attribute;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttributeService {
    Attribute save(Attribute attribute);
    List<Attribute> findAll();
    Optional<Attribute> findById(Long id);
    void deleteById(Long id);
    List<Attribute> findByName(String name);
    List<Attribute> findByExpiryDateBefore(LocalDate date);
    List<Attribute> findByStockLessThan(Integer minStock);
}
