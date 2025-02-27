package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.Discount;
import aptech.vn.backend.repository.DiscountRepository;
import aptech.vn.backend.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Override
    public Optional<Discount> findById(Long id) {
        return discountRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }

    @Override
    public Optional<Discount> findByCode(String code) {
        return discountRepository.findByCode(code);
    }

    @Override
    public List<Discount> findByMedicineId(Long medicineId) {
        return discountRepository.findByMedicineId(medicineId);
    }

    @Override
    public List<Discount> findByStartDateBeforeAndEndDateAfter(LocalDateTime now, LocalDateTime now2) {
        return discountRepository.findByStartDateBeforeAndEndDateAfter(now, now2);
    }

    @Override
    public List<Discount> findByEndDateBefore(LocalDateTime now) {
        return discountRepository.findByEndDateBefore(now);
    }

    @Override
    public List<Discount> findActiveDiscounts() {
        LocalDateTime now = LocalDateTime.now();
        return discountRepository.findByStartDateBeforeAndEndDateAfter(now, now);
    }

    @Override
    public List<Discount> findExpiredDiscounts() {
        return discountRepository.findByEndDateBefore(LocalDateTime.now());
    }
}