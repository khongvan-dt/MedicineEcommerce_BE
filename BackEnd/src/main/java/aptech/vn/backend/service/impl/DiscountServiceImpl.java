package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.DiscountDTO;
import aptech.vn.backend.entity.Discount;
import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.mapper.DiscountMapper;
import aptech.vn.backend.repository.DiscountRepository;
import aptech.vn.backend.repository.MedicineRepository;
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
    private final MedicineRepository medicineRepository;
    private final DiscountMapper discountMapper;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository,
                               MedicineRepository medicineRepository,
                               DiscountMapper discountMapper) {
        this.discountRepository = discountRepository;
        this.medicineRepository = medicineRepository;
        this.discountMapper = discountMapper;
    }

    @Override
    public List<DiscountDTO.GetDiscountDto> findAll() {
        return discountRepository.findAll().stream()
                .map(discountMapper::toGetDiscountDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DiscountDTO.GetDiscountDto> findById(Long id) {
        return discountRepository.findById(id)
                .map(discountMapper::toGetDiscountDto);
    }

    @Override
    @Transactional
    public DiscountDTO.GetDiscountDto saveOrUpdate(DiscountDTO.SaveDiscountDto discountDTO) {
        Discount discount;

        if (discountDTO.getId() == null || discountDTO.getId() == 0) {
            // INSERT case
            discount = new Discount();
            discount.setCreatedAt(LocalDateTime.now());
            discount.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Discount> existingDiscount = discountRepository.findById(discountDTO.getId());
            if (existingDiscount.isEmpty()) {
                throw new RuntimeException("Discount not found with ID: " + discountDTO.getId());
            }
            discount = existingDiscount.get();
            discount.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý medicine relationship
        if (discountDTO.getMedicineId() != null) {
            Medicine medicine = medicineRepository.findById(discountDTO.getMedicineId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found with ID: " + discountDTO.getMedicineId()));
            discount.setMedicine(medicine);
        }

        // Cập nhật các trường khác
        discount.setCode(discountDTO.getCode());
        discount.setName(discountDTO.getName());
        discount.setDiscountPercentage(discountDTO.getDiscountPercentage());
        discount.setStartDate(discountDTO.getStartDate());
        discount.setEndDate(discountDTO.getEndDate());

        Discount savedDiscount = discountRepository.save(discount);
        return discountMapper.toGetDiscountDto(savedDiscount);
    }

    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }

    @Override
    public Optional<DiscountDTO.GetDiscountDto> findByCode(String code) {
        return discountRepository.findByCode(code)
                .map(discountMapper::toGetDiscountDto);
    }

    @Override
    public List<DiscountDTO.GetDiscountDto> findByMedicineId(Long medicineId) {
        return discountRepository.findByMedicine_Id(medicineId).stream()
                .map(discountMapper::toGetDiscountDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO.GetDiscountDto> findByActive(LocalDateTime now) {
        return discountRepository.findByStartDateBeforeAndEndDateAfter(now, now).stream()
                .map(discountMapper::toGetDiscountDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO.GetDiscountDto> findByDiscountPercentageGreaterThanEqual(Double percentage) {
        return discountRepository.findByDiscountPercentageGreaterThanEqual(percentage).stream()
                .map(discountMapper::toGetDiscountDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO.GetDiscountDto> findByExpired(LocalDateTime date) {
        return discountRepository.findByEndDateBefore(date).stream()
                .map(discountMapper::toGetDiscountDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO.GetDiscountDto> findByNoExpiration() {
        return discountRepository.findByEndDateIsNull().stream()
                .map(discountMapper::toGetDiscountDto)
                .collect(Collectors.toList());
    }
}