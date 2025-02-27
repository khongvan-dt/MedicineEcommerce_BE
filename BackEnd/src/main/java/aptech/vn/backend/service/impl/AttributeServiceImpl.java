package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.Attribute;
import aptech.vn.backend.repository.AttributeRepository;
import aptech.vn.backend.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;

    @Autowired
    public AttributeServiceImpl(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public Attribute save(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @Override
    public List<Attribute> findAll() {
        return attributeRepository.findAll();
    }

    @Override
    public Optional<Attribute> findById(Long id) {
        return attributeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        attributeRepository.deleteById(id);
    }

    @Override
    public List<Attribute> findByName(String name) {
        return attributeRepository.findByName(name);
    }

    @Override
    public List<Attribute> findByExpiryDateBefore(LocalDate date) {
        return attributeRepository.findByExpiryDateBefore(date);
    }

    @Override
    public List<Attribute> findByStockLessThan(Integer minStock) {
        return attributeRepository.findByStockLessThan(minStock);
    }
}