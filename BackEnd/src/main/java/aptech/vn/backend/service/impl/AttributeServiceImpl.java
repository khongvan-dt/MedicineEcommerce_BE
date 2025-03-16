package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.AttributeDTO;
import aptech.vn.backend.entity.Attribute;
import aptech.vn.backend.mapper.AttributeMapper;
import aptech.vn.backend.repository.AttributeRepository;
import aptech.vn.backend.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeMapper attributeMapper;

    @Autowired
    public AttributeServiceImpl(AttributeRepository attributeRepository, AttributeMapper attributeMapper) {
        this.attributeRepository = attributeRepository;
        this.attributeMapper = attributeMapper;
    }

    @Override
    public List<AttributeDTO.GetAttributeDto> findAll() {
        return attributeRepository.findAll().stream()
                .map(attributeMapper::toGetAttributeDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AttributeDTO.GetAttributeDto> findById(Long id) {
        return attributeRepository.findById(id)
                .map(attributeMapper::toGetAttributeDto);
    }

    @Override
    public AttributeDTO.GetAttributeDto saveOrUpdate(AttributeDTO.SaveAttributeDto attributeDTO) {
        Attribute attribute;

        if (attributeDTO.getId() == null || attributeDTO.getId() == 0) {
            // INSERT case
            attribute = attributeMapper.toAttributeEntity(attributeDTO);
            attribute.setCreatedAt(LocalDateTime.now());
            attribute.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Attribute> existingAttribute = attributeRepository.findById(attributeDTO.getId());
            if (existingAttribute.isEmpty()) {
                throw new RuntimeException("Attribute not found with ID: " + attributeDTO.getId());
            }
            attribute = existingAttribute.get();
            attribute.setName(attributeDTO.getName());
            attribute.setPriceIn(attributeDTO.getPriceIn());
            attribute.setPriceOut(attributeDTO.getPriceOut());
            attribute.setStock(attributeDTO.getStock());
            attribute.setExpiryDate(attributeDTO.getExpiryDate());
            attribute.setUpdatedAt(LocalDateTime.now());
        }

        Attribute savedAttribute = attributeRepository.save(attribute);
        return attributeMapper.toGetAttributeDto(savedAttribute);
    }

    @Override
    public void deleteById(Long id) {
        attributeRepository.deleteById(id);
    }

    @Override
    public List<AttributeDTO.GetAttributeDto> findByName(String name) {
        return attributeRepository.findByName(name).stream()
                .map(attributeMapper::toGetAttributeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttributeDTO.GetAttributeDto> findByPriceInLessThanEqual(BigDecimal price) {
        return attributeRepository.findByPriceInLessThanEqual(price).stream()
                .map(attributeMapper::toGetAttributeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttributeDTO.GetAttributeDto> findByPriceOutLessThanEqual(BigDecimal price) {
        return attributeRepository.findByPriceOutLessThanEqual(price).stream()
                .map(attributeMapper::toGetAttributeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttributeDTO.GetAttributeDto> findByStockGreaterThan(Integer stock) {
        return attributeRepository.findByStockGreaterThan(stock).stream()
                .map(attributeMapper::toGetAttributeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttributeDTO.GetAttributeDto> findByExpiryDateBefore(LocalDate date) {
        return attributeRepository.findByExpiryDateBefore(date).stream()
                .map(attributeMapper::toGetAttributeDto)
                .collect(Collectors.toList());
    }
}