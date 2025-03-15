package aptech.vn.backend.service;

import aptech.vn.backend.dto.VoucherDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoucherService {
    List<VoucherDTO.GetDto> findAll();
    Optional<VoucherDTO.GetDto> findById(Long id);
    VoucherDTO.GetDto saveOrUpdate(VoucherDTO.SaveDto voucherDTO);
    void deleteById(Long id);
    Optional<VoucherDTO.GetDto> findByCode(String code);
    List<VoucherDTO.GetDto> findByStockGreaterThan(Integer minStock);
    List<VoucherDTO.GetDto> findByVoucherPercentageGreaterThanEqual(Double percentage);
    List<VoucherDTO.GetDto> findActive(LocalDateTime now);
    List<VoucherDTO.GetDto> findExpired(LocalDateTime date);
    List<VoucherDTO.GetDto> findNeverExpires();
}