package aptech.vn.backend.controller;

import aptech.vn.backend.entity.Discount;
import aptech.vn.backend.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        List<Discount> discounts = discountService.findAll();
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable Long id) {
        return discountService.findById(id)
                .map(discount -> new ResponseEntity<>(discount, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
        Discount savedDiscount = discountService.save(discount);
        return new ResponseEntity<>(savedDiscount, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable Long id, @RequestBody Discount discount) {
        return discountService.findById(id)
                .map(existingDiscount -> {
                    discount.setId(id);
                    Discount updatedDiscount = discountService.save(discount);
                    return new ResponseEntity<>(updatedDiscount, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        return discountService.findById(id)
                .map(discount -> {
                    discountService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Discount> getDiscountByCode(@PathVariable String code) {
        return discountService.findByCode(code)
                .map(discount -> new ResponseEntity<>(discount, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<Discount>> getDiscountsByMedicineId(@PathVariable Long medicineId) {
        List<Discount> discounts = discountService.findByMedicineId(medicineId);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Discount>> getActiveDiscounts() {
        List<Discount> discounts = discountService.findActiveDiscounts();
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping("/expired")
    public ResponseEntity<List<Discount>> getExpiredDiscounts() {
        List<Discount> discounts = discountService.findExpiredDiscounts();
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping("/time-range")
    public ResponseEntity<List<Discount>> getDiscountsByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Discount> discounts = discountService.findByStartDateBeforeAndEndDateAfter(end, start);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping("/expired-before")
    public ResponseEntity<List<Discount>> getDiscountsExpiredBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<Discount> discounts = discountService.findByEndDateBefore(date);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }
}