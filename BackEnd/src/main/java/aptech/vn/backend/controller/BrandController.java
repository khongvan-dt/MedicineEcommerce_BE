package aptech.vn.backend.controller;

import aptech.vn.backend.DTO.BrandDTO;
import aptech.vn.backend.entity.Brand;
import aptech.vn.backend.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.findAll();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        return brandService.findById(id)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody BrandDTO brandDTO) {
        Brand brand = Brand.builder()
                .name(brandDTO.getName())
                .image(brandDTO.getImage())
                .build();

        Brand savedBrand = brandService.save(brand);
        return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        return brandService.findById(id)
                .map(existingBrand -> {
                    existingBrand.setName(brandDTO.getName());
                    existingBrand.setImage(brandDTO.getImage());

                    Brand updatedBrand = brandService.save(existingBrand);
                    return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        return brandService.findById(id)
                .map(brand -> {
                    brandService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Brand> getBrandByName(@PathVariable String name) {
        return brandService.findByName(name)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Brand>> getBrandsContainingName(@RequestParam String name) {
        List<Brand> brands = brandService.findByNameContaining(name);
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }
}
