package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);
    List<Category> findByParent_Id(Long parentId);
    List<Category> findByParentIsNull();
}