package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.ServiceTypeDTO;
import aptech.vn.backend.entity.ServiceType;
import aptech.vn.backend.mapper.ServiceTypeMapper;
import aptech.vn.backend.repository.ServiceTypeRepository;
import aptech.vn.backend.service.ServiceTypeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceTypeMapper serviceTypeMapper;

    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository, ServiceTypeMapper serviceTypeMapper) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.serviceTypeMapper = serviceTypeMapper;
    }

    @Override
    public List<ServiceTypeDTO.GetDto> findAll() {
        return serviceTypeRepository.findAll().stream()
                .map(serviceTypeMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceTypeDTO.GetDto> findById(Long id) {
        return serviceTypeRepository.findById(id)
                .map(serviceTypeMapper::toGetDto);
    }

    @Override
    public ServiceTypeDTO.GetDto saveOrUpdate(ServiceTypeDTO.SaveDto serviceTypeDTO) {
        ServiceType serviceType;

        if (serviceTypeDTO.getId() == null || serviceTypeDTO.getId() == 0) {
            // INSERT case
            serviceType = new ServiceType();
            serviceType.setCreatedAt(LocalDateTime.now());
            serviceType.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<ServiceType> existingType = serviceTypeRepository.findById(serviceTypeDTO.getId());
            if (existingType.isEmpty()) {
                throw new RuntimeException("Service type not found with ID: " + serviceTypeDTO.getId());
            }
            serviceType = existingType.get();
            serviceType.setUpdatedAt(LocalDateTime.now());
        }

        // Cập nhật tên
        serviceType.setName(serviceTypeDTO.getName());

        ServiceType savedType = serviceTypeRepository.save(serviceType);
        return serviceTypeMapper.toGetDto(savedType);
    }

    @Override
    public void deleteById(Long id) {
        serviceTypeRepository.deleteById(id);
    }

    @Override
    public Optional<ServiceTypeDTO.GetDto> findByName(String name) {
        return serviceTypeRepository.findByName(name)
                .map(serviceTypeMapper::toGetDto);
    }

    @Override
    public Page<ServiceTypeDTO.GetDto> findAll(Pageable pageable) {
        return serviceTypeRepository.findAll(pageable)
                .map(serviceTypeMapper::toGetDto);
    }

    @Override
    public List<ServiceTypeDTO.GetDto> findByNameContaining(String namePattern) {
        return serviceTypeRepository.findByNameContaining(namePattern).stream()
                .map(serviceTypeMapper::toGetDto)
                .collect(Collectors.toList());
    }
}