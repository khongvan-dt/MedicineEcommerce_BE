package aptech.vn.backend.service;

import aptech.vn.backend.dto.BrandDTO;
import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandDTO> findAll();
    Optional<BrandDTO> findById(Long id);
    BrandDTO save(BrandDTO brandDTO);
    void deleteById(Long id);
    Optional<BrandDTO> findByName(String name);
    List<BrandDTO> findByNameContaining(String namePattern);
}