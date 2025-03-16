package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.VouchersDTO;
import aptech.vn.backend.entity.Vouchers;
import aptech.vn.backend.mapper.VouchersMapper;
import aptech.vn.backend.repository.VouchersRepository;
import aptech.vn.backend.service.VouchersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VouchersServiceImpl implements VouchersService {
        private final VouchersMapper vouchersMapper;
     private final VouchersRepository VouchersRepository;

    public VouchersServiceImpl(VouchersRepository VouchersRepository, VouchersMapper vouchersMapper) {
        this.VouchersRepository = VouchersRepository;
        this.vouchersMapper = vouchersMapper;
    }

    @Override
    public List<VouchersDTO.GetVouchersDto> findAll() {
        return VouchersRepository.findAll()
                .stream()
                .map(vouchersMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VouchersDTO.GetVouchersDto> findById(Long id) {
        return VouchersRepository.findById(id).map(vouchersMapper::toGetDto);
    }

    @Override
    @Transactional
    public VouchersDTO.GetVouchersDto saveOrUpdate(VouchersDTO.SaveVouchersDto VouchersDTO) {
        Vouchers Vouchers;

        if (VouchersDTO.getId() == null || VouchersDTO.getId() == 0) {
            // INSERT case
            Vouchers = vouchersMapper.toEntity(VouchersDTO);
            Vouchers.setCreatedAt(LocalDateTime.now());
            Vouchers.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Vouchers> existingVouchers = VouchersRepository.findById(VouchersDTO.getId());
            if (existingVouchers.isEmpty()) {
                throw new RuntimeException("Vouchers not found with ID: " + VouchersDTO.getId());
            }

            Vouchers = existingVouchers.get();
            Vouchers.setCode(VouchersDTO.getCode());
            Vouchers.setVoucherPercentage(VouchersDTO.getVoucherPercentage());
            Vouchers.setStock(VouchersDTO.getStock());
            Vouchers.setStartDate(VouchersDTO.getStartDate());
            Vouchers.setEndDate(VouchersDTO.getEndDate());
            Vouchers.setUpdatedAt(LocalDateTime.now());
        }

        Vouchers savedVouchers = VouchersRepository.save(Vouchers);
        return vouchersMapper.toGetDto(savedVouchers);
    }

    @Override
    public void deleteById(Long id) {
        VouchersRepository.deleteById(id);
    }

    @Override
    public Optional<VouchersDTO.GetVouchersDto> findByCode(String code) {
        return VouchersRepository.findByCode(code).map(vouchersMapper::toGetDto);
    }

    @Override
    public List<VouchersDTO.GetVouchersDto> findByStockGreaterThan(Integer minStock) {
        return VouchersRepository.findByStockGreaterThan(minStock)
                .stream()
                .map(vouchersMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VouchersDTO.GetVouchersDto> findByVoucherPercentageGreaterThanEqual(Double percentage) {
        return List.of();
    }



    @Override
    public List<VouchersDTO.GetVouchersDto> findActive(LocalDateTime now) {
        return VouchersRepository.findByStartDateBeforeAndEndDateAfter(now, now)
                .stream()
                .map(vouchersMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VouchersDTO.GetVouchersDto> findExpired(LocalDateTime date) {
        return VouchersRepository.findByEndDateBefore(date)
                .stream()
                .map(vouchersMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VouchersDTO.GetVouchersDto> findNeverExpires() {
        return VouchersRepository.findByEndDateIsNull()
                .stream()
                .map(vouchersMapper::toGetDto)
                .collect(Collectors.toList());
    }
}