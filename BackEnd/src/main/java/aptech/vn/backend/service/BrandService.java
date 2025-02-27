package aptech.vn.backend.service;

import aptech.vn.backend.entity.Brand;
import java.util.List;
import java.util.Optional;

public interface BrandService {
    Brand save(Brand brand);
    List<Brand> findAll();
    Optional<Brand> findById(Long id);
    void deleteById(Long id);
    Optional<Brand> findByName(String name);
    List<Brand> findByNameContaining(String name);
}