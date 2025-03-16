package aptech.vn.backend.controller;

import aptech.vn.backend.dto.CategoryDTO;
import aptech.vn.backend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO.GetCategoryDto>> getAllCategories() {
        List<CategoryDTO.GetCategoryDto> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO.GetCategoryDto> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryDTO.GetCategoryDto> saveOrUpdateCategory(@RequestBody CategoryDTO.SaveCategoryDto categoryDTO) {
        CategoryDTO.GetCategoryDto savedCategory = categoryService.saveOrUpdate(categoryDTO);
        return ResponseEntity.ok(savedCategory);
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
    public ResponseEntity<List<CategoryDTO.GetCategoryDto>> findByName(@RequestParam String name) {
        List<CategoryDTO.GetCategoryDto> categories = categoryService.findByName(name);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/search/parent")
    public ResponseEntity<List<CategoryDTO.GetCategoryDto>> findByParentId(@RequestParam Long parentId) {
        List<CategoryDTO.GetCategoryDto> categories = categoryService.findByParentId(parentId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/roots")
    public ResponseEntity<List<CategoryDTO.GetCategoryDto>> findRootCategories() {
        List<CategoryDTO.GetCategoryDto> categories = categoryService.findRootCategories();
        return ResponseEntity.ok(categories);
    }
}