package aptech.vn.backend.service;

import aptech.vn.backend.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoucherService {
    Voucher save(Voucher voucher);
    Optional<Voucher> findById(Long id);
    List<Voucher> findAll();
    Page<Voucher> findAll(Pageable pageable);
    void deleteById(Long id);
    Optional<Voucher> findByCode(String code);
    List<Voucher> findByMedicineId(Long medicineId);
    List<Voucher> findActiveVouchers(LocalDateTime currentTime);
    boolean isVoucherValid(String code, Long medicineId);
    boolean useVoucher(String code);
}