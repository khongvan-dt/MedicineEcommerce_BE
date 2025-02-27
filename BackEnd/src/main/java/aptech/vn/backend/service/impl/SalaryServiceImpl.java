package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.PaymentStatus;
import aptech.vn.backend.entity.Salary;
import aptech.vn.backend.repository.SalaryRepository;
import aptech.vn.backend.service.SalaryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;

    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public Optional<Salary> findById(Long id) {
        return salaryRepository.findById(id);
    }

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public Page<Salary> findAll(Pageable pageable) {
        return salaryRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        salaryRepository.deleteById(id);
    }

    @Override
    public List<Salary> findByUserId(Long userId) {
        return salaryRepository.findByUserId(userId);
    }

    @Override
    public List<Salary> findByStatus(PaymentStatus status) {
        return salaryRepository.findByStatus(status);
    }

    @Override
    public List<Salary> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end) {
        return salaryRepository.findByCreatedAtBetween(start, end);
    }

    @Override
    public BigDecimal getTotalSalaryByUserIdAndPeriod(Long userId, LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public boolean updateStatus(Long salaryId, PaymentStatus newStatus) {
        return false;
    }
}
