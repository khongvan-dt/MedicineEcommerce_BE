package aptech.vn.backend.controller;

import aptech.vn.backend.dto.VoucherDTO;
import aptech.vn.backend.service.VoucherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vouchers")
@CrossOrigin("*")
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public ResponseEntity<List<VoucherDTO.GetDto>> getAllVouchers() {
        List<VoucherDTO.GetDto> vouchers = voucherService.findAll();
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDTO.GetDto> getVoucherById(@PathVariable Long id) {
        return voucherService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<VoucherDTO.GetDto> saveOrUpdateVoucher(@RequestBody VoucherDTO.SaveDto voucherDTO) {
        VoucherDTO.GetDto savedVoucher = voucherService.saveOrUpdate(voucherDTO);
        return ResponseEntity.ok(savedVoucher);
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
    public ResponseEntity<VoucherDTO.GetDto> getVoucherByCode(@RequestParam String code) {
        return voucherService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-stock")
    public ResponseEntity<List<VoucherDTO.GetDto>> getVouchersByStock(@RequestParam Integer minStock) {
        return ResponseEntity.ok(voucherService.findByStockGreaterThan(minStock));
    }

    @GetMapping("/by-percentage")
    public ResponseEntity<List<VoucherDTO.GetDto>> getVouchersByPercentage(@RequestParam Double percentage) {
        return ResponseEntity.ok(voucherService.findByVoucherPercentageGreaterThanEqual(percentage));
    }

    @GetMapping("/active")
    public ResponseEntity<List<VoucherDTO.GetDto>> getActiveVouchers() {
        return ResponseEntity.ok(voucherService.findActive(LocalDateTime.now()));
    }

    @GetMapping("/expired")
    public ResponseEntity<List<VoucherDTO.GetDto>> getExpiredVouchers() {
        return ResponseEntity.ok(voucherService.findExpired(LocalDateTime.now()));
    }

    @GetMapping("/never-expires")
    public ResponseEntity<List<VoucherDTO.GetDto>> getNeverExpiresVouchers() {
        return ResponseEntity.ok(voucherService.findNeverExpires());
    }
}