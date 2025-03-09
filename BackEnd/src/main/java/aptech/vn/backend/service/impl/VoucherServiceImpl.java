package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.VoucherDTO;
import aptech.vn.backend.entity.Voucher;
import aptech.vn.backend.mapper.VoucherMapper;
import aptech.vn.backend.repository.VoucherRepository;
import aptech.vn.backend.service.VoucherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {
    private final VoucherMapper voucherMapper;
    private final VoucherRepository voucherRepository;

    public VoucherServiceImpl(VoucherRepository voucherRepository, VoucherMapper voucherMapper) {
        this.voucherRepository = voucherRepository;
        this.voucherMapper = voucherMapper;
    }

    @Override
    public List<VoucherDTO> findAll() {
        return voucherRepository.findAll()
                .stream()
                .map(voucherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VoucherDTO> findById(Long id) {
        return voucherRepository.findById(id).map(voucherMapper::toDto);
    }

    @Override
    public VoucherDTO save(VoucherDTO voucherDTO) {
        Voucher voucher = voucherMapper.toEntity(voucherDTO);
        voucher = voucherRepository.save(voucher);
        return voucherMapper.toDto(voucher);
    }

    @Override
    public void deleteById(Long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public Optional<VoucherDTO> findByCode(String code) {
        return voucherRepository.findByCode(code).map(voucherMapper::toDto);
    }

    @Override
    public List<VoucherDTO> findByStockGreaterThan(Integer minStock) {
        return voucherRepository.findByStockGreaterThan(minStock)
                .stream()
                .map(voucherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherDTO> findByVoucherPercentageGreaterThanEqual(Double percentage) {
        return voucherRepository.findByVoucherPercentageGreaterThanEqual(percentage)
                .stream()
                .map(voucherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherDTO> findActive(LocalDateTime now) {
        return voucherRepository.findByEndDateBefore(now)
                .stream()
                .map(voucherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherDTO> findExpired(LocalDateTime date) {
        return voucherRepository.findByStartDateBeforeAndEndDateAfter(date, date)
                .stream()
                .map(voucherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherDTO> findNeverExpires() {
        return voucherRepository.findByEndDateIsNull()
                .stream()
                .map(voucherMapper::toDto)
                .collect(Collectors.toList());
    }
}
