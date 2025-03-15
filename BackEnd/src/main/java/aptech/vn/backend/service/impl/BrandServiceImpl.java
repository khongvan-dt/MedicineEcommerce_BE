package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.BrandDTO;
import aptech.vn.backend.entity.Brand;
import aptech.vn.backend.mapper.BrandMapper;
import aptech.vn.backend.repository.BrandRepository;
import aptech.vn.backend.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<BrandDTO.GetDto> findAll() {
        return brandRepository.findAll().stream()
                .map(brandMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BrandDTO.GetDto> findById(Long id) {
        return brandRepository.findById(id)
                .map(brandMapper::toGetDto);
    }

    @Override
    public BrandDTO.GetDto saveOrUpdate(BrandDTO.SaveDto brandDTO) {
        Brand brand;

        if (brandDTO.getId() == null || brandDTO.getId() == 0) {
            // INSERT case
            brand = brandMapper.toEntity(brandDTO);
            brand.setCreatedAt(LocalDateTime.now()); // Đặt createdAt khi tạo mới
            brand.setUpdatedAt(LocalDateTime.now()); // Cập nhật updatedAt khi tạo mới
        } else {
            // UPDATE case
            Optional<Brand> existingBrand = brandRepository.findById(brandDTO.getId());
            if (existingBrand.isEmpty()) {
                throw new RuntimeException("Brand not found with ID: " + brandDTO.getId());
            }
            brand = existingBrand.get();
            brand.setName(brandDTO.getName());
            brand.setImage(brandDTO.getImage());
            brand.setUpdatedAt(LocalDateTime.now()); // Chỉ cập nhật updatedAt khi update
        }

        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toGetDto(savedBrand);
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Optional<BrandDTO.GetDto> findByName(String name) {
        return brandRepository.findByName(name)
                .map(brandMapper::toGetDto);
    }

    @Override
    public List<BrandDTO.GetDto> findByNameContaining(String namePattern) {
        return brandRepository.findByNameContaining(namePattern).stream()
                .map(brandMapper::toGetDto)
                .collect(Collectors.toList());
    }
}
