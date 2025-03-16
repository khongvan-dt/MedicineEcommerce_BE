package aptech.vn.backend.service;

import aptech.vn.backend.dto.ServiceTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceTypeService {
    List<ServiceTypeDTO.GetServiceTypeDto> findAll();
    Optional<ServiceTypeDTO.GetServiceTypeDto> findById(Long id);
    ServiceTypeDTO.GetServiceTypeDto saveOrUpdate(ServiceTypeDTO.SaveServiceTypeDto serviceTypeDTO);
    void deleteById(Long id);
    Optional<ServiceTypeDTO.GetServiceTypeDto> findByName(String name);
    Page<ServiceTypeDTO.GetServiceTypeDto> findAll(Pageable pageable);
    List<ServiceTypeDTO.GetServiceTypeDto> findByNameContaining(String namePattern);
}