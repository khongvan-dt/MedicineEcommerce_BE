package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.Voucher;
import aptech.vn.backend.repository.VoucherRepository;
import aptech.vn.backend.service.VoucherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;

    public VoucherServiceImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public Voucher save(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return voucherRepository.findById(id);
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Page<Voucher> findAll(Pageable pageable) {
        return voucherRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public Optional<Voucher> findByCode(String code) {
        return voucherRepository.findByCode(code);
    }

    @Override
    public List<Voucher> findByMedicineId(Long medicineId) {
        return voucherRepository.findByMedicineId(medicineId);
    }

    @Override
    public List<Voucher> findActiveVouchers(LocalDateTime currentTime) {
        return List.of();
    }

    @Override
    public boolean isVoucherValid(String code, Long medicineId) {
        return false;
    }

    @Override
    public boolean useVoucher(String code) {
        return false;
    }
}
