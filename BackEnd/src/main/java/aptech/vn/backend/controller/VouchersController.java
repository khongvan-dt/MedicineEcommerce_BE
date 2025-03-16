package aptech.vn.backend.controller;

import aptech.vn.backend.dto.VouchersDTO;
import aptech.vn.backend.service.VouchersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vouchers")
@CrossOrigin("*")
public class VouchersController {

    private final VouchersService voucherService;

    public VouchersController(VouchersService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public ResponseEntity<List<VouchersDTO.GetVouchersDto>> getAllVouchers() {
        List<VouchersDTO.GetVouchersDto> vouchers = voucherService.findAll();
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VouchersDTO.GetVouchersDto> getVoucherById(@PathVariable Long id) {
        return voucherService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<VouchersDTO.GetVouchersDto> saveOrUpdateVoucher(@RequestBody VouchersDTO.SaveVouchersDto voucherDTO) {
        VouchersDTO.GetVouchersDto savedVoucher = voucherService.saveOrUpdate(voucherDTO);
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
    public ResponseEntity<VouchersDTO.GetVouchersDto> getVoucherByCode(@RequestParam String code) {
        return voucherService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-stock")
    public ResponseEntity<List<VouchersDTO.GetVouchersDto>> getVouchersByStock(@RequestParam Integer minStock) {
        return ResponseEntity.ok(voucherService.findByStockGreaterThan(minStock));
    }

    @GetMapping("/by-percentage")
    public ResponseEntity<List<VouchersDTO.GetVouchersDto>> getVouchersByPercentage(@RequestParam Double percentage) {
        return ResponseEntity.ok(voucherService.findByVoucherPercentageGreaterThanEqual(percentage));
    }

    @GetMapping("/active")
    public ResponseEntity<List<VouchersDTO.GetVouchersDto>> getActiveVouchers() {
        return ResponseEntity.ok(voucherService.findActive(LocalDateTime.now()));
    }

    @GetMapping("/expired")
    public ResponseEntity<List<VouchersDTO.GetVouchersDto>> getExpiredVouchers() {
        return ResponseEntity.ok(voucherService.findExpired(LocalDateTime.now()));
    }

    @GetMapping("/never-expires")
    public ResponseEntity<List<VouchersDTO.GetVouchersDto>> getNeverExpiresVouchers() {
        return ResponseEntity.ok(voucherService.findNeverExpires());
    }
}