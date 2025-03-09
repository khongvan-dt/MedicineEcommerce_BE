package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.ReviewDTO;
import aptech.vn.backend.entity.Review;
import aptech.vn.backend.mapper.ReviewMapper;
import aptech.vn.backend.repository.ReviewRepository;
import aptech.vn.backend.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDTO> findById(Long id) {
        return reviewRepository.findById(id).map(reviewMapper::toDto);
    }

    @Override
    public ReviewDTO save(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);
        review = reviewRepository.save(review);
        return reviewMapper.toDto(review);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewDTO> findByUserId(Long userId) {
        return reviewRepository.findByUser_Id(userId)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByRating(Integer rating) {
        return reviewRepository.findByRating(rating)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByRatingGreaterThanEqual(Integer minRating) {
        return reviewRepository.findByRatingGreaterThanEqual(minRating)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByDoctorId(Long doctorId) {
        return reviewRepository.findByDoctor_Id(doctorId)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByMedicineId(Long medicineId) {
        return reviewRepository.findByMedicine_Id(medicineId)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByServiceId(Long serviceId) {
        return reviewRepository.findByService_Id(serviceId)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByDoctorIdAndMinRating(Long doctorId, Integer minRating) {
        return reviewRepository.findByDoctor_IdAndRatingGreaterThanEqual(doctorId, minRating)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByMedicineIdAndMinRating(Long medicineId, Integer minRating) {
        return reviewRepository.findByMedicine_IdAndRatingGreaterThanEqual(medicineId, minRating)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByServiceIdAndMinRating(Long serviceId, Integer minRating) {
        return reviewRepository.findByService_IdAndRatingGreaterThanEqual(serviceId, minRating)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }
}
