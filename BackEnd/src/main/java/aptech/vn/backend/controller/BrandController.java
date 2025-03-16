package aptech.vn.backend.controller;

import aptech.vn.backend.dto.BrandDTO;
import aptech.vn.backend.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/brands")
@CrossOrigin("*")

public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandDTO.GetBrandDto>> getAllBrands() {
        List<BrandDTO.GetBrandDto> brands = brandService.findAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO.GetBrandDto> getBrandById(@PathVariable Long id) {
        return brandService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<BrandDTO.GetBrandDto> saveOrUpdateBrand(@RequestBody BrandDTO.SaveBrandDto brandDTO) {
        BrandDTO.GetBrandDto savedBrand = brandService.saveOrUpdate(brandDTO);
        return ResponseEntity.ok(savedBrand);
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
    public ResponseEntity<BrandDTO.GetBrandDto> findByName(@RequestParam String name) {
        return brandService.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/name-containing")
    public ResponseEntity<List<BrandDTO.GetBrandDto>> findByNameContaining(@RequestParam String namePattern) {
        List<BrandDTO.GetBrandDto> brands = brandService.findByNameContaining(namePattern);
        return ResponseEntity.ok(brands);
    }
}
