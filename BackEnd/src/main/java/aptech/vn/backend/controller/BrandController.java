package aptech.vn.backend.controller;

import aptech.vn.backend.dto.BrandDTO;
import aptech.vn.backend.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> brands = brandService.findAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        Optional<BrandDTO> brand = brandService.findById(id);
        return brand.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO savedBrand = brandService.save(brandDTO);
        return ResponseEntity.ok(savedBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        if (!brandService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        BrandDTO updatedBrand = brandService.save(brandDTO);
        return ResponseEntity.ok(updatedBrand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        if (!brandService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        brandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    public ResponseEntity<BrandDTO> findByName(@RequestParam String name) {
        Optional<BrandDTO> brand = brandService.findByName(name);
        return brand.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/name-containing")
    public ResponseEntity<List<BrandDTO>> findByNameContaining(@RequestParam String namePattern) {
        List<BrandDTO> brands = brandService.findByNameContaining(namePattern);
        return ResponseEntity.ok(brands);
    }
}
