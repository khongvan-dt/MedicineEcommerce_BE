package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.Service;
import aptech.vn.backend.repository.ServiceRepository;
import aptech.vn.backend.service.ServiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Optional<Service> findById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Page<Service> findAll(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<Service> findByNameContaining(String keyword) {
        return serviceRepository.findByNameContaining(keyword);
    }

    @Override
    public List<Service> findByPriceLessThan(BigDecimal maxPrice) {
        return serviceRepository.findByPriceLessThanEqual(maxPrice);
    }

    @Override
    public List<Service> findByPriceGreaterThan(BigDecimal minPrice) {
        return serviceRepository.findByPriceGreaterThanEqual(minPrice);
    }

    @Override
    public List<Service> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return List.of();
    }
}
