package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.VoucherDTO;
import aptech.vn.backend.entity.Voucher;
import aptech.vn.backend.mapper.VoucherMapper;
import aptech.vn.backend.repository.VoucherRepository;
import aptech.vn.backend.service.VoucherService;
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
    public List<VoucherDTO.GetDto> findAll() {
        return voucherRepository.findAll()
                .stream()
                .map(voucherMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VoucherDTO.GetDto> findById(Long id) {
        return voucherRepository.findById(id).map(voucherMapper::toGetDto);
    }

    @Override
    @Transactional
    public VoucherDTO.GetDto saveOrUpdate(VoucherDTO.SaveDto voucherDTO) {
        Voucher voucher;

        if (voucherDTO.getId() == null || voucherDTO.getId() == 0) {
            // INSERT case
            voucher = voucherMapper.toEntity(voucherDTO);
            voucher.setCreatedAt(LocalDateTime.now());
            voucher.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Voucher> existingVoucher = voucherRepository.findById(voucherDTO.getId());
            if (existingVoucher.isEmpty()) {
                throw new RuntimeException("Voucher not found with ID: " + voucherDTO.getId());
            }

            voucher = existingVoucher.get();
            voucher.setCode(voucherDTO.getCode());
            voucher.setVoucherPercentage(voucherDTO.getVoucherPercentage());
            voucher.setStock(voucherDTO.getStock());
            voucher.setStartDate(voucherDTO.getStartDate());
            voucher.setEndDate(voucherDTO.getEndDate());
            voucher.setUpdatedAt(LocalDateTime.now());
        }

        Voucher savedVoucher = voucherRepository.save(voucher);
        return voucherMapper.toGetDto(savedVoucher);
    }

    @Override
    public void deleteById(Long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public Optional<VoucherDTO.GetDto> findByCode(String code) {
        return voucherRepository.findByCode(code).map(voucherMapper::toGetDto);
    }

    @Override
    public List<VoucherDTO.GetDto> findByStockGreaterThan(Integer minStock) {
        return voucherRepository.findByStockGreaterThan(minStock)
                .stream()
                .map(voucherMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherDTO.GetDto> findByVoucherPercentageGreaterThanEqual(Double percentage) {
        return voucherRepository.findByVoucherPercentageGreaterThanEqual(percentage)
                .stream()
                .map(voucherMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherDTO.GetDto> findActive(LocalDateTime now) {
        return voucherRepository.findByStartDateBeforeAndEndDateAfter(now, now)
                .stream()
                .map(voucherMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherDTO.GetDto> findExpired(LocalDateTime date) {
        return voucherRepository.findByEndDateBefore(date)
                .stream()
                .map(voucherMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherDTO.GetDto> findNeverExpires() {
        return voucherRepository.findByEndDateIsNull()
                .stream()
                .map(voucherMapper::toGetDto)
                .collect(Collectors.toList());
    }
}