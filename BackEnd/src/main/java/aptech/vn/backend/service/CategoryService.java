package aptech.vn.backend.service;

import aptech.vn.backend.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category save(Category category);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void deleteById(Long id);
    Optional<Category> findByName(String name);
    List<Category> findByParentId(Long parentId);
    List<Category> findByParentIsNull();
    List<Category> findByNameContaining(String name);
}
