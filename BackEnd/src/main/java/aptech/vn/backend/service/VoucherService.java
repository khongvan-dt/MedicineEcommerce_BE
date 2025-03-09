package aptech.vn.backend.service;

import aptech.vn.backend.dto.VoucherDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoucherService {
    List<VoucherDTO> findAll();
    Optional<VoucherDTO> findById(Long id);
    VoucherDTO save(VoucherDTO voucherDTO);
    void deleteById(Long id);
    Optional<VoucherDTO> findByCode(String code);
    List<VoucherDTO> findByStockGreaterThan(Integer minStock);
    List<VoucherDTO> findByVoucherPercentageGreaterThanEqual(Double percentage);
    List<VoucherDTO> findActive(LocalDateTime now);
    List<VoucherDTO> findExpired(LocalDateTime date);
    List<VoucherDTO> findNeverExpires();
}