package aptech.vn.backend.controller;

import aptech.vn.backend.dto.DiscountDTO;
import aptech.vn.backend.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@CrossOrigin("*")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<List<DiscountDTO.GetDto>> getAllDiscounts() {
        List<DiscountDTO.GetDto> discounts = discountService.findAll();
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO.GetDto> getDiscountById(@PathVariable Long id) {
        return discountService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<DiscountDTO.GetDto> saveOrUpdateDiscount(@RequestBody DiscountDTO.SaveDto discountDTO) {
        DiscountDTO.GetDto savedDiscount = discountService.saveOrUpdate(discountDTO);
        return ResponseEntity.ok(savedDiscount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        if (!discountService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        discountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/code/{code}")
    public ResponseEntity<DiscountDTO.GetDto> findByCode(@PathVariable String code) {
        return discountService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/medicine/{medicineId}")
    public ResponseEntity<List<DiscountDTO.GetDto>> findByMedicineId(@PathVariable Long medicineId) {
        List<DiscountDTO.GetDto> discounts = discountService.findByMedicineId(medicineId);
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/search/active")
    public ResponseEntity<List<DiscountDTO.GetDto>> findByActive() {
        List<DiscountDTO.GetDto> discounts = discountService.findByActive(LocalDateTime.now());
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/search/percentage")
    public ResponseEntity<List<DiscountDTO.GetDto>> findByDiscountPercentageGreaterThanEqual(@RequestParam Double percentage) {
        List<DiscountDTO.GetDto> discounts = discountService.findByDiscountPercentageGreaterThanEqual(percentage);
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/search/expired")
    public ResponseEntity<List<DiscountDTO.GetDto>> findByExpired() {
        List<DiscountDTO.GetDto> discounts = discountService.findByExpired(LocalDateTime.now());
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/search/no-expiration")
    public ResponseEntity<List<DiscountDTO.GetDto>> findByNoExpiration() {
        List<DiscountDTO.GetDto> discounts = discountService.findByNoExpiration();
        return ResponseEntity.ok(discounts);
    }
}