package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.ServiceDTO;
import aptech.vn.backend.entity.Service;
import aptech.vn.backend.mapper.ServiceMapper;
import aptech.vn.backend.repository.ServiceRepository;
import aptech.vn.backend.service.ServiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceMapper serviceMapper;

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public List<ServiceDTO> findAll() {
        return serviceRepository.findAll()
                .stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceDTO> findById(Long id) {
        return serviceRepository.findById(id).map(serviceMapper::toDto);
    }

    @Override
    public ServiceDTO save(ServiceDTO serviceDTO) {
        Service service = serviceMapper.toEntity(serviceDTO);
        serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<ServiceDTO> findByName(String name) {
        return serviceRepository.findByName(name)
                .stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO> findByNameContaining(String namePattern) {
        return serviceRepository.findByNameContaining(namePattern)
                .stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO> findByPriceLessThanEqual(BigDecimal maxPrice) {
        return serviceRepository.findByPriceLessThanEqual(maxPrice)
                .stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO> findByPriceGreaterThanEqual(BigDecimal minPrice) {
        return serviceRepository.findByPriceGreaterThanEqual(minPrice)
                .stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return serviceRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }
}
