package aptech.vn.backend.service;

import aptech.vn.backend.dto.VouchersDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VouchersService {
    List<VouchersDTO.GetVouchersDto> findAll();
    Optional<VouchersDTO.GetVouchersDto> findById(Long id);
    VouchersDTO.GetVouchersDto saveOrUpdate(VouchersDTO.SaveVouchersDto VouchersDTO);
    void deleteById(Long id);
    Optional<VouchersDTO.GetVouchersDto> findByCode(String code);
    List<VouchersDTO.GetVouchersDto> findByStockGreaterThan(Integer minStock);
    List<VouchersDTO.GetVouchersDto> findByVoucherPercentageGreaterThanEqual(Double percentage);
    List<VouchersDTO.GetVouchersDto> findActive(LocalDateTime now);
    List<VouchersDTO.GetVouchersDto> findExpired(LocalDateTime date);
    List<VouchersDTO.GetVouchersDto> findNeverExpires();
}