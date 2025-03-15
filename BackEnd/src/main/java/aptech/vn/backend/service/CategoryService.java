package aptech.vn.backend.service;

import aptech.vn.backend.dto.CategoryDTO;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO.GetDto> findAll();
    Optional<CategoryDTO.GetDto> findById(Long id);
    CategoryDTO.GetDto saveOrUpdate(CategoryDTO.SaveDto categoryDTO);
    void deleteById(Long id);
    List<CategoryDTO.GetDto> findByName(String name);
    List<CategoryDTO.GetDto> findByParentId(Long parentId);
    List<CategoryDTO.GetDto> findRootCategories();
}