package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.SalaryDTO;
import aptech.vn.backend.entity.PaymentStatus;
import aptech.vn.backend.entity.Salary;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.mapper.SalaryMapper;
import aptech.vn.backend.repository.SalaryRepository;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.service.SalaryService;
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
    private final UserRepository userRepository;

    public SalaryServiceImpl(
            SalaryRepository salaryRepository,
            UserRepository userRepository,
            SalaryMapper salaryMapper) {
        this.salaryRepository = salaryRepository;
        this.userRepository = userRepository;
        this.salaryMapper = salaryMapper;
    }

    @Override
    public List<SalaryDTO.GetDto> findAll() {
        return salaryRepository.findAll()
                .stream()
                .map(salaryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SalaryDTO.GetDto> findById(Long id) {
        return salaryRepository.findById(id)
                .map(salaryMapper::toGetDto);
    }

    @Override
    @Transactional
    public SalaryDTO.GetDto saveOrUpdate(SalaryDTO.SaveDto salaryDTO) {
        Salary salary;

        if (salaryDTO.getId() == null || salaryDTO.getId() == 0) {
            // INSERT case
            salary = new Salary();
            salary.setCreatedAt(LocalDateTime.now());
            salary.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Salary> existingSalary = salaryRepository.findById(salaryDTO.getId());
            if (existingSalary.isEmpty()) {
                throw new RuntimeException("Salary not found with ID: " + salaryDTO.getId());
            }
            salary = existingSalary.get();
            salary.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý user relationship
        if (salaryDTO.getUserId() != null) {
            User user = userRepository.findById(salaryDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + salaryDTO.getUserId()));
            salary.setUser(user);
        }

        // Cập nhật các trường khác
        salary.setBankCode(salaryDTO.getBankCode());
        salary.setBankName(salaryDTO.getBankName());
        salary.setPrice(salaryDTO.getPrice());
        salary.setStatus(salaryDTO.getStatus());

        Salary savedSalary = salaryRepository.save(salary);
        return salaryMapper.toGetDto(savedSalary);
    }

    @Override
    public void deleteById(Long id) {
        salaryRepository.deleteById(id);
    }

    @Override
    public List<SalaryDTO.GetDto> findByUserId(Long userId) {
        return salaryRepository.findByUser_Id(userId)
                .stream()
                .map(salaryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO.GetDto> findByBankCode(String bankCode) {
        return salaryRepository.findByBankCode(bankCode)
                .stream()
                .map(salaryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO.GetDto> findByBankName(String bankName) {
        return salaryRepository.findByBankName(bankName)
                .stream()
                .map(salaryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO.GetDto> findByStatus(PaymentStatus status) {
        return salaryRepository.findByStatus(status)
                .stream()
                .map(salaryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO.GetDto> findByPriceGreaterThanEqual(BigDecimal amount) {
        return salaryRepository.findByPriceGreaterThanEqual(amount)
                .stream()
                .map(salaryMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO.GetDto> findByCreatedBetween(LocalDateTime start, LocalDateTime end) {
        return salaryRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(salaryMapper::toGetDto)
                .collect(Collectors.toList());
    }
}