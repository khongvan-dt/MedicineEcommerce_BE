package aptech.vn.backend.service;

import aptech.vn.backend.dto.BrandDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandDTO.GetBrandDto> findAll();
    Optional<BrandDTO.GetBrandDto> findById(Long id);
    BrandDTO.GetBrandDto saveOrUpdate(BrandDTO.SaveBrandDto brandDTO);
    void deleteById(Long id);
    Optional<BrandDTO.GetBrandDto> findByName(String name);
    List<BrandDTO.GetBrandDto> findByNameContaining(String namePattern);

}
