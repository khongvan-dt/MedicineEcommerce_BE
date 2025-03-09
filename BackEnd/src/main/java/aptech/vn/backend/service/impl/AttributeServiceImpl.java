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
    public List<AttributeDTO> findAll() {
        return attributeRepository.findAll().stream()
                .map(attributeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AttributeDTO> findById(Long id) {
        return attributeRepository.findById(id)
                .map(attributeMapper::toDto);
    }

    @Override
    public AttributeDTO save(AttributeDTO attributeDTO) {
        Attribute attribute = attributeMapper.toEntity(attributeDTO);
        Attribute savedAttribute = attributeRepository.save(attribute);
        return attributeMapper.toDto(savedAttribute);
    }

    @Override
    public void deleteById(Long id) {
        attributeRepository.deleteById(id);
    }

    @Override
    public List<AttributeDTO> findByName(String name) {
        return attributeRepository.findByName(name).stream()
                .map(attributeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttributeDTO> findByPriceInLessThanEqual(BigDecimal price) {
        return attributeRepository.findByPriceInLessThanEqual(price).stream()
                .map(attributeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttributeDTO> findByPriceOutLessThanEqual(BigDecimal price) {
        return attributeRepository.findByPriceOutLessThanEqual(price).stream()
                .map(attributeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttributeDTO> findByStockGreaterThan(Integer stock) {
        return attributeRepository.findByStockGreaterThan(stock).stream()
                .map(attributeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttributeDTO> findByExpiryDateBefore(LocalDate date) {
        return attributeRepository.findByExpiryDateBefore(date).stream()
                .map(attributeMapper::toDto)
                .collect(Collectors.toList());
    }
}