package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.ServiceDTO;
import aptech.vn.backend.entity.Service;
import aptech.vn.backend.mapper.ServiceMapper;
import aptech.vn.backend.repository.ServiceRepository;
import aptech.vn.backend.service.ServiceService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    public List<ServiceDTO.GetServiceDto> findAll() {
        return serviceRepository.findAll()
                .stream()
                .map(serviceMapper::toGetServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceDTO.GetServiceDto> findById(Long id) {
        return serviceRepository.findById(id)
                .map(serviceMapper::toGetServiceDto);
    }

    @Override
    @Transactional
    public ServiceDTO.GetServiceDto saveOrUpdate(ServiceDTO.SaveServiceDto serviceDTO) {
        Service service;

        if (serviceDTO.getId() == null || serviceDTO.getId() == 0) {
            // INSERT case
            service = new Service();
            service.setCreatedAt(LocalDateTime.now());
            service.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Service> existingService = serviceRepository.findById(serviceDTO.getId());
            if (existingService.isEmpty()) {
                throw new RuntimeException("Service not found with ID: " + serviceDTO.getId());
            }
            service = existingService.get();
            service.setUpdatedAt(LocalDateTime.now());
        }

        // Cập nhật các trường
        service.setName(serviceDTO.getName());
        service.setPrice(serviceDTO.getPrice());
        service.setDescription(serviceDTO.getDescription());

        Service savedService = serviceRepository.save(service);
        return serviceMapper.toGetServiceDto(savedService);
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<ServiceDTO.GetServiceDto> findByName(String name) {
        return serviceRepository.findByName(name)
                .stream()
                .map(serviceMapper::toGetServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO.GetServiceDto> findByNameContaining(String namePattern) {
        return serviceRepository.findByNameContaining(namePattern)
                .stream()
                .map(serviceMapper::toGetServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO.GetServiceDto> findByPriceLessThanEqual(BigDecimal maxPrice) {
        return serviceRepository.findByPriceLessThanEqual(maxPrice)
                .stream()
                .map(serviceMapper::toGetServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO.GetServiceDto> findByPriceGreaterThanEqual(BigDecimal minPrice) {
        return serviceRepository.findByPriceGreaterThanEqual(minPrice)
                .stream()
                .map(serviceMapper::toGetServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO.GetServiceDto> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return serviceRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(serviceMapper::toGetServiceDto)
                .collect(Collectors.toList());
    }
}