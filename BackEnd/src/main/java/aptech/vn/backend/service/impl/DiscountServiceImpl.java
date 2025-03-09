package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.DiscountDTO;
import aptech.vn.backend.entity.Discount;
import aptech.vn.backend.mapper.DiscountMapper;
import aptech.vn.backend.repository.DiscountRepository;
import aptech.vn.backend.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository, DiscountMapper discountMapper) {
        this.discountRepository = discountRepository;
        this.discountMapper = discountMapper;
    }

    @Override
    public List<DiscountDTO> findAll() {
        return discountRepository.findAll().stream()
                .map(discountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DiscountDTO> findById(Long id) {
        return discountRepository.findById(id)
                .map(discountMapper::toDto);
    }

    @Override
    public DiscountDTO save(DiscountDTO discountDTO) {
        Discount discount = discountMapper.toEntity(discountDTO);
        Discount savedDiscount = discountRepository.save(discount);
        return discountMapper.toDto(savedDiscount);
    }

    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }

    @Override
    public Optional<DiscountDTO> findByCode(String code) {
        return discountRepository.findByCode(code)
                .map(discountMapper::toDto);
    }

    @Override
    public List<DiscountDTO> findByMedicineId(Long medicineId) {
        return discountRepository.findByMedicine_Id(medicineId).stream()
                .map(discountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO> findByActive(LocalDateTime now) {
        return discountRepository.findByStartDateBeforeAndEndDateAfter(now, now).stream()
                .map(discountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO> findByDiscountPercentageGreaterThanEqual(Double percentage) {
        return discountRepository.findByDiscountPercentageGreaterThanEqual(percentage).stream()
                .map(discountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO> findByExpired(LocalDateTime date) {
        return discountRepository.findByEndDateBefore(date).stream()
                .map(discountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO> findByNoExpiration() {
        return discountRepository.findByEndDateIsNull().stream()
                .map(discountMapper::toDto)
                .collect(Collectors.toList());
    }
}