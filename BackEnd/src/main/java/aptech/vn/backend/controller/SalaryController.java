package aptech.vn.backend.controller;

import aptech.vn.backend.entity.PaymentStatus;
import aptech.vn.backend.entity.Salary;
import aptech.vn.backend.service.SalaryService;
import aptech.vn.backend.service.impl.SalaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryServiceImpl salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping
    public ResponseEntity<List<Salary>> getAllSalaries() {
        return ResponseEntity.ok(salaryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salary> getSalaryById(@PathVariable Long id) {
        return salaryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Salary>> getSalariesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(salaryService.findByUserId(userId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Salary>> getSalariesByStatus(@PathVariable PaymentStatus status) {
        return ResponseEntity.ok(salaryService.findByStatus(status));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Salary>> getSalariesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(salaryService.findByCreatedAtBetween(start, end));
    }

    @GetMapping("/user/{userId}/total")
    public ResponseEntity<BigDecimal> getTotalSalaryByUserIdAndPeriod(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(salaryService.getTotalSalaryByUserIdAndPeriod(userId, start, end));
    }

    @PostMapping
    public ResponseEntity<Salary> createSalary(@RequestBody Salary salary) {
        return new ResponseEntity<>(salaryService.save(salary), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long id, @RequestBody Salary salary) {
        return salaryService.findById(id)
                .map(existingSalary -> {
                    salary.setId(id);
                    return ResponseEntity.ok(salaryService.save(salary));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateSalaryStatus(
            @PathVariable Long id,
            @RequestBody Map<String, PaymentStatus> statusMap) {

        PaymentStatus newStatus = statusMap.get("status");
        if (newStatus == null) {
            return ResponseEntity.badRequest().body("Status field is required");
        }

        return salaryService.findById(id)
                .map(existingSalary -> {
                    boolean updated = salaryService.updateStatus(id, newStatus);
                    if (updated) {
                        return ResponseEntity.ok("Salary status updated successfully");
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to update salary status");
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        return salaryService.findById(id)
                .map(salary -> {
                    salaryService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}