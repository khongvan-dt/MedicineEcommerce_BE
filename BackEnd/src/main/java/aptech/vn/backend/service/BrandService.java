package aptech.vn.backend.service;

import aptech.vn.backend.dto.BrandDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandDTO.GetDto> findAll();
    Optional<BrandDTO.GetDto> findById(Long id);
    BrandDTO.GetDto saveOrUpdate(BrandDTO.SaveDto brandDTO);
    void deleteById(Long id);
    Optional<BrandDTO.GetDto> findByName(String name);
    List<BrandDTO.GetDto> findByNameContaining(String namePattern);

}
