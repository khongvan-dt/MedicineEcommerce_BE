package aptech.vn.backend.controller;

import aptech.vn.backend.dto.SalaryDTO;
import aptech.vn.backend.entity.PaymentStatus;
import aptech.vn.backend.service.SalaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping
    public ResponseEntity<List<SalaryDTO>> getAllSalaries() {
        List<SalaryDTO> salaries = salaryService.findAll();
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryDTO> getSalaryById(@PathVariable Long id) {
        Optional<SalaryDTO> salary = salaryService.findById(id);
        return salary.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SalaryDTO> createSalary(@RequestBody SalaryDTO salaryDTO) {
        SalaryDTO savedSalary = salaryService.save(salaryDTO);
        return ResponseEntity.ok(savedSalary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaryDTO> updateSalary(@PathVariable Long id, @RequestBody SalaryDTO salaryDTO) {
        if (!salaryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        SalaryDTO updatedSalary = salaryService.save(salaryDTO);
        return ResponseEntity.ok(updatedSalary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        if (!salaryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        salaryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<SalaryDTO>> getSalariesByUserId(@PathVariable Long userId) {
        List<SalaryDTO> salaries = salaryService.findByUserId(userId);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/by-bank-code/{bankCode}")
    public ResponseEntity<List<SalaryDTO>> getSalariesByBankCode(@PathVariable String bankCode) {
        List<SalaryDTO> salaries = salaryService.findByBankCode(bankCode);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/by-bank-name/{bankName}")
    public ResponseEntity<List<SalaryDTO>> getSalariesByBankName(@PathVariable String bankName) {
        List<SalaryDTO> salaries = salaryService.findByBankName(bankName);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<SalaryDTO>> getSalariesByStatus(@PathVariable PaymentStatus status) {
        List<SalaryDTO> salaries = salaryService.findByStatus(status);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/by-min-price/{amount}")
    public ResponseEntity<List<SalaryDTO>> getSalariesByMinPrice(@PathVariable BigDecimal amount) {
        List<SalaryDTO> salaries = salaryService.findByPriceGreaterThanEqual(amount);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<SalaryDTO>> getSalariesByDateRange(
            @RequestParam("start") LocalDateTime start,
            @RequestParam("end") LocalDateTime end) {
        List<SalaryDTO> salaries = salaryService.findByCreatedBetween(start, end);
        return ResponseEntity.ok(salaries);
    }
}
