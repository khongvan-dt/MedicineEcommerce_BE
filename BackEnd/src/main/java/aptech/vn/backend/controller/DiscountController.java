package aptech.vn.backend.controller;

import aptech.vn.backend.dto.DiscountDTO;
import aptech.vn.backend.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<List<DiscountDTO>> getAllDiscounts() {
        List<DiscountDTO> discounts = discountService.findAll();
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> getDiscountById(@PathVariable Long id) {
        Optional<DiscountDTO> discount = discountService.findById(id);
        return discount.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DiscountDTO> createDiscount(@RequestBody DiscountDTO discountDTO) {
        DiscountDTO savedDiscount = discountService.save(discountDTO);
        return ResponseEntity.ok(savedDiscount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountDTO> updateDiscount(@PathVariable Long id, @RequestBody DiscountDTO discountDTO) {
        if (!discountService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        DiscountDTO updatedDiscount = discountService.save(discountDTO);
        return ResponseEntity.ok(updatedDiscount);
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
    public ResponseEntity<Optional<DiscountDTO>> findByCode(@PathVariable String code) {
        Optional<DiscountDTO> discount = discountService.findByCode(code);
        return ResponseEntity.ok(discount);
    }

    @GetMapping("/search/medicine/{medicineId}")
    public ResponseEntity<List<DiscountDTO>> findByMedicineId(@PathVariable Long medicineId) {
        List<DiscountDTO> discounts = discountService.findByMedicineId(medicineId);
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/search/active")
    public ResponseEntity<List<DiscountDTO>> findByActive() {
        List<DiscountDTO> discounts = discountService.findByActive(LocalDateTime.now());
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/search/percentage")
    public ResponseEntity<List<DiscountDTO>> findByDiscountPercentageGreaterThanEqual(@RequestParam Double percentage) {
        List<DiscountDTO> discounts = discountService.findByDiscountPercentageGreaterThanEqual(percentage);
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/search/expired")
    public ResponseEntity<List<DiscountDTO>> findByExpired() {
        List<DiscountDTO> discounts = discountService.findByExpired(LocalDateTime.now());
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/search/no-expiration")
    public ResponseEntity<List<DiscountDTO>> findByNoExpiration() {
        List<DiscountDTO> discounts = discountService.findByNoExpiration();
        return ResponseEntity.ok(discounts);
    }
}
