package aptech.vn.backend.controller;

import aptech.vn.backend.dto.VoucherDTO;
import aptech.vn.backend.service.VoucherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public ResponseEntity<List<VoucherDTO>> getAllVouchers() {
        List<VoucherDTO> vouchers = voucherService.findAll();
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDTO> getVoucherById(@PathVariable Long id) {
        Optional<VoucherDTO> voucher = voucherService.findById(id);
        return voucher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VoucherDTO> createVoucher(@RequestBody VoucherDTO voucherDTO) {
        VoucherDTO savedVoucher = voucherService.save(voucherDTO);
        return ResponseEntity.ok(savedVoucher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoucherDTO> updateVoucher(@PathVariable Long id, @RequestBody VoucherDTO voucherDTO) {
        if (!voucherService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        VoucherDTO updatedVoucher = voucherService.save(voucherDTO);
        return ResponseEntity.ok(updatedVoucher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoucher(@PathVariable Long id) {
        if (!voucherService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        voucherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-code")
    public ResponseEntity<VoucherDTO> getVoucherByCode(@RequestParam String code) {
        Optional<VoucherDTO> voucher = voucherService.findByCode(code);
        return voucher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-stock")
    public ResponseEntity<List<VoucherDTO>> getVouchersByStock(@RequestParam Integer minStock) {
        return ResponseEntity.ok(voucherService.findByStockGreaterThan(minStock));
    }

    @GetMapping("/by-percentage")
    public ResponseEntity<List<VoucherDTO>> getVouchersByPercentage(@RequestParam Double percentage) {
        return ResponseEntity.ok(voucherService.findByVoucherPercentageGreaterThanEqual(percentage));
    }

    @GetMapping("/active")
    public ResponseEntity<List<VoucherDTO>> getActiveVouchers() {
        return ResponseEntity.ok(voucherService.findActive(LocalDateTime.now()));
    }

    @GetMapping("/expired")
    public ResponseEntity<List<VoucherDTO>> getExpiredVouchers() {
        return ResponseEntity.ok(voucherService.findExpired(LocalDateTime.now()));
    }

    @GetMapping("/never-expires")
    public ResponseEntity<List<VoucherDTO>> getNeverExpiresVouchers() {
        return ResponseEntity.ok(voucherService.findNeverExpires());
    }
}
