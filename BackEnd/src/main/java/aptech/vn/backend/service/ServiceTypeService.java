package aptech.vn.backend.service;

import aptech.vn.backend.dto.ServiceTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceTypeService {
    List<ServiceTypeDTO.GetDto> findAll();
    Optional<ServiceTypeDTO.GetDto> findById(Long id);
    ServiceTypeDTO.GetDto saveOrUpdate(ServiceTypeDTO.SaveDto serviceTypeDTO);
    void deleteById(Long id);
    Optional<ServiceTypeDTO.GetDto> findByName(String name);
    Page<ServiceTypeDTO.GetDto> findAll(Pageable pageable);
    List<ServiceTypeDTO.GetDto> findByNameContaining(String namePattern);
}