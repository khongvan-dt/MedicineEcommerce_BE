package aptech.vn.backend.controller;

import aptech.vn.backend.entity.Voucher;
import aptech.vn.backend.service.VoucherService;
import aptech.vn.backend.service.impl.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {

    private final VoucherService voucherService;

    @Autowired
    public VoucherController(VoucherServiceImpl voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public ResponseEntity<List<Voucher>> getAllVouchers() {
        return ResponseEntity.ok(voucherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voucher> getVoucherById(@PathVariable Long id) {
        return voucherService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Voucher> getVoucherByCode(@PathVariable String code) {
        return voucherService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<Voucher>> getVouchersByMedicineId(@PathVariable Long medicineId) {
        return ResponseEntity.ok(voucherService.findByMedicineId(medicineId));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Voucher>> getActiveVouchers(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime currentTime) {
        LocalDateTime time = currentTime != null ? currentTime : LocalDateTime.now();
        return ResponseEntity.ok(voucherService.findActiveVouchers(time));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateVoucher(
            @RequestParam String code,
            @RequestParam Long medicineId) {
        return ResponseEntity.ok(voucherService.isVoucherValid(code, medicineId));
    }

    @PostMapping
    public ResponseEntity<Voucher> createVoucher(@RequestBody Voucher voucher) {
        return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable Long id, @RequestBody Voucher voucher) {
        return voucherService.findById(id)
                .map(existingVoucher -> {
                    voucher.setId(id);
                    return ResponseEntity.ok(voucherService.save(voucher));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/use")
    public ResponseEntity<String> useVoucher(@RequestBody Map<String, String> request) {
        String code = request.get("code");

        if (code == null || code.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Voucher code is required");
        }

        boolean used = voucherService.useVoucher(code);
        if (used) {
            return ResponseEntity.ok("Voucher used successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to use voucher. It may be invalid or already used");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoucher(@PathVariable Long id) {
        return voucherService.findById(id)
                .map(voucher -> {
                    voucherService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}