package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.ServiceType;
import aptech.vn.backend.repository.ServiceTypeRepository;
import aptech.vn.backend.service.ServiceTypeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public ServiceType save(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public Optional<ServiceType> findById(Long id) {
        return serviceTypeRepository.findById(id);
    }

    @Override
    public List<ServiceType> findAll() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public Page<ServiceType> findAll(Pageable pageable) {
        return serviceTypeRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        serviceTypeRepository.deleteById(id);
    }

    @Override
    public Optional<ServiceType> findByName(String name) {
        return serviceTypeRepository.findByName(name);
    }
}
