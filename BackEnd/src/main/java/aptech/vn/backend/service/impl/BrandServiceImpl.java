package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.BrandDTO;
import aptech.vn.backend.entity.Brand;
import aptech.vn.backend.mapper.BrandMapper;
import aptech.vn.backend.repository.BrandRepository;
import aptech.vn.backend.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream()
                .map(brandMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BrandDTO> findById(Long id) {
        return brandRepository.findById(id)
                .map(brandMapper::toDto);
    }

    @Override
    public BrandDTO save(BrandDTO brandDTO) {
        Brand brand = brandMapper.toEntity(brandDTO);
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toDto(savedBrand);
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Optional<BrandDTO> findByName(String name) {
        return brandRepository.findByName(name)
                .map(brandMapper::toDto);
    }

    @Override
    public List<BrandDTO> findByNameContaining(String namePattern) {
        return brandRepository.findByNameContaining(namePattern).stream()
                .map(brandMapper::toDto)
                .collect(Collectors.toList());
    }
}