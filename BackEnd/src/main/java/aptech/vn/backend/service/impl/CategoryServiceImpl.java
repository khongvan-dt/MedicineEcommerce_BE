package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.CategoryDTO;
import aptech.vn.backend.entity.Category;
import aptech.vn.backend.mapper.CategoryMapper;
import aptech.vn.backend.repository.CategoryRepository;
import aptech.vn.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO.GetDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDTO.GetDto> findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toGetDto);
    }

    @Override
    public CategoryDTO.GetDto saveOrUpdate(CategoryDTO.SaveDto categoryDTO) {
        Category category;

        if (categoryDTO.getId() == null || categoryDTO.getId() == 0) {
            // INSERT case
            category = new Category();
            category.setName(categoryDTO.getName());

            // Xử lý parent nếu có
            if (categoryDTO.getParentId() != null && categoryDTO.getParentId() > 0) {
                Category parent = categoryRepository.findById(categoryDTO.getParentId())
                        .orElseThrow(() -> new RuntimeException("Parent category not found with ID: " + categoryDTO.getParentId()));
                category.setParent(parent);
            }

            category.setCreatedAt(LocalDateTime.now());
            category.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Category> existingCategory = categoryRepository.findById(categoryDTO.getId());
            if (existingCategory.isEmpty()) {
                throw new RuntimeException("Category not found with ID: " + categoryDTO.getId());
            }

            category = existingCategory.get();
            category.setName(categoryDTO.getName());

            // Xử lý parent nếu có thay đổi
            if (categoryDTO.getParentId() != null) {
                if (categoryDTO.getParentId() > 0) {
                    Category parent = categoryRepository.findById(categoryDTO.getParentId())
                            .orElseThrow(() -> new RuntimeException("Parent category not found with ID: " + categoryDTO.getParentId()));
                    category.setParent(parent);
                } else {
                    // Nếu parentId = 0, đặt category thành root
                    category.setParent(null);
                }
            }

            category.setUpdatedAt(LocalDateTime.now());
        }

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toGetDto(savedCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDTO.GetDto> findByName(String name) {
        return categoryRepository.findByName(name).stream()
                .map(categoryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO.GetDto> findByParentId(Long parentId) {
        return categoryRepository.findByParent_Id(parentId).stream()
                .map(categoryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO.GetDto> findRootCategories() {
        return categoryRepository.findByParentIsNull().stream()
                .map(categoryMapper::toGetDto)
                .collect(Collectors.toList());
    }
}