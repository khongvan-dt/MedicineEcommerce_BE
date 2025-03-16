package aptech.vn.backend.service;

import aptech.vn.backend.dto.CategoryDTO;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO.GetCategoryDto> findAll();
    Optional<CategoryDTO.GetCategoryDto> findById(Long id);
    CategoryDTO.GetCategoryDto saveOrUpdate(CategoryDTO.SaveCategoryDto categoryDTO);
    void deleteById(Long id);
    List<CategoryDTO.GetCategoryDto> findByName(String name);
    List<CategoryDTO.GetCategoryDto> findByParentId(Long parentId);
    List<CategoryDTO.GetCategoryDto> findRootCategories();
}