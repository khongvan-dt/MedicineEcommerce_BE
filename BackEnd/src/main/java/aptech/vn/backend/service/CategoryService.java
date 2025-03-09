package aptech.vn.backend.service;

import aptech.vn.backend.dto.CategoryDTO;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO> findAll();
    Optional<CategoryDTO> findById(Long id);
    CategoryDTO save(CategoryDTO categoryDTO);
    void deleteById(Long id);
    List<CategoryDTO> findByName(String name);
    List<CategoryDTO> findByParentId(Long parentId);
    List<CategoryDTO> findRootCategories();
}