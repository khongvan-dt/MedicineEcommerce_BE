package aptech.vn.backend.controller;

import aptech.vn.backend.dto.SalaryDTO;
import aptech.vn.backend.entity.PaymentStatus;
import aptech.vn.backend.service.SalaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/salaries")
@CrossOrigin("*")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping
    public ResponseEntity<List<SalaryDTO.GetSalaryDto>> getAllSalaries() {
        List<SalaryDTO.GetSalaryDto> salaries = salaryService.findAll();
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryDTO.GetSalaryDto> getSalaryById(@PathVariable Long id) {
        return salaryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<SalaryDTO.GetSalaryDto> saveOrUpdateSalary(@RequestBody SalaryDTO.SaveSalaryDto salaryDTO) {
        SalaryDTO.GetSalaryDto savedSalary = salaryService.saveOrUpdate(salaryDTO);
        return ResponseEntity.ok(savedSalary);
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
    public ResponseEntity<List<SalaryDTO.GetSalaryDto>> getSalariesByUserId(@PathVariable Long userId) {
        List<SalaryDTO.GetSalaryDto> salaries = salaryService.findByUserId(userId);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/by-bank-code/{bankCode}")
    public ResponseEntity<List<SalaryDTO.GetSalaryDto>> getSalariesByBankCode(@PathVariable String bankCode) {
        List<SalaryDTO.GetSalaryDto> salaries = salaryService.findByBankCode(bankCode);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/by-bank-name/{bankName}")
    public ResponseEntity<List<SalaryDTO.GetSalaryDto>> getSalariesByBankName(@PathVariable String bankName) {
        List<SalaryDTO.GetSalaryDto> salaries = salaryService.findByBankName(bankName);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<SalaryDTO.GetSalaryDto>> getSalariesByStatus(@PathVariable PaymentStatus status) {
        List<SalaryDTO.GetSalaryDto> salaries = salaryService.findByStatus(status);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/by-min-price/{amount}")
    public ResponseEntity<List<SalaryDTO.GetSalaryDto>> getSalariesByMinPrice(@PathVariable BigDecimal amount) {
        List<SalaryDTO.GetSalaryDto> salaries = salaryService.findByPriceGreaterThanEqual(amount);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<SalaryDTO.GetSalaryDto>> getSalariesByDateRange(
            @RequestParam("start") LocalDateTime start,
            @RequestParam("end") LocalDateTime end) {
        List<SalaryDTO.GetSalaryDto> salaries = salaryService.findByCreatedBetween(start, end);
        return ResponseEntity.ok(salaries);
    }
}