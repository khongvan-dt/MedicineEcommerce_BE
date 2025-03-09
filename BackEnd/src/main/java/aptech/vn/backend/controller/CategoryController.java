package aptech.vn.backend.controller;

import aptech.vn.backend.dto.CategoryDTO;
import aptech.vn.backend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Optional<CategoryDTO> category = categoryService.findById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = categoryService.save(categoryDTO);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        if (!categoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CategoryDTO updatedCategory = categoryService.save(categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (!categoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<CategoryDTO>> findByName(@RequestParam String name) {
        List<CategoryDTO> categories = categoryService.findByName(name);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/search/parent")
    public ResponseEntity<List<CategoryDTO>> findByParentId(@RequestParam Long parentId) {
        List<CategoryDTO> categories = categoryService.findByParentId(parentId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/roots")
    public ResponseEntity<List<CategoryDTO>> findRootCategories() {
        List<CategoryDTO> categories = categoryService.findRootCategories();
        return ResponseEntity.ok(categories);
    }
}
