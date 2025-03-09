package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.SalaryDTO;
import aptech.vn.backend.entity.PaymentStatus;
import aptech.vn.backend.entity.Salary;
import aptech.vn.backend.mapper.SalaryMapper;
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
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {
    private final SalaryMapper salaryMapper;
    private final SalaryRepository salaryRepository;

    public SalaryServiceImpl(SalaryRepository salaryRepository, SalaryMapper salaryMapper) {
        this.salaryRepository = salaryRepository;
        this.salaryMapper = salaryMapper;
    }

    @Override
    public List<SalaryDTO> findAll() {
        return salaryRepository.findAll()
                .stream()
                .map(salaryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SalaryDTO> findById(Long id) {
        return salaryRepository.findById(id).map(salaryMapper::toDto);
    }

    @Override
    public SalaryDTO save(SalaryDTO salaryDTO) {
        Salary salary = salaryMapper.toEntity(salaryDTO);
        salary = salaryRepository.save(salary);
        return salaryMapper.toDto(salary);
    }

    @Override
    public void deleteById(Long id) {
        salaryRepository.deleteById(id);
    }

    @Override
    public List<SalaryDTO> findByUserId(Long userId) {
        return salaryRepository.findByUser_Id(userId)
                .stream()
                .map(salaryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO> findByBankCode(String bankCode) {
        return salaryRepository.findByBankCode(bankCode)
                .stream()
                .map(salaryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO> findByBankName(String bankName) {
        return salaryRepository.findByBankName(bankName)
                .stream()
                .map(salaryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO> findByStatus(PaymentStatus status) {
        return salaryRepository.findByStatus(status)
                .stream()
                .map(salaryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO> findByPriceGreaterThanEqual(BigDecimal amount) {
        return salaryRepository.findByPriceGreaterThanEqual(amount)
                .stream()
                .map(salaryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO> findByCreatedBetween(LocalDateTime start, LocalDateTime end) {
        return salaryRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(salaryMapper::toDto)
                .collect(Collectors.toList());
    }
}
