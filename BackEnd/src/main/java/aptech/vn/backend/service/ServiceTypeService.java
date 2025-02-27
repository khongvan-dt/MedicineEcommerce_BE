package aptech.vn.backend.service;

import aptech.vn.backend.entity.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceTypeService {
    ServiceType save(ServiceType serviceType);
    Optional<ServiceType> findById(Long id);
    List<ServiceType> findAll();
    Page<ServiceType> findAll(Pageable pageable);
    void deleteById(Long id);
    Optional<ServiceType> findByName(String name);
}