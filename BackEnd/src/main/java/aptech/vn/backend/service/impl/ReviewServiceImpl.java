package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.ReviewDTO;
import aptech.vn.backend.entity.DoctorProfile;
import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.entity.Review;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.mapper.ReviewMapper;
import aptech.vn.backend.repository.DoctorProfileRepository;
import aptech.vn.backend.repository.MedicineRepository;
import aptech.vn.backend.repository.ReviewRepository;
import aptech.vn.backend.repository.ServiceRepository;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.service.ReviewService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final DoctorProfileRepository doctorRepository;
    private final MedicineRepository medicineRepository;
    private final ServiceRepository serviceRepository;

    public ReviewServiceImpl(
            ReviewRepository reviewRepository,
            UserRepository userRepository,
            DoctorProfileRepository doctorRepository,
            MedicineRepository medicineRepository,
            ServiceRepository serviceRepository,
            ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.medicineRepository = medicineRepository;
        this.serviceRepository = serviceRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDTO.GetReviewDto> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toGetReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDTO.GetReviewDto> findById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toGetReviewDto);
    }

    @Override
    @Transactional
    public ReviewDTO.GetReviewDto saveOrUpdate(ReviewDTO.SaveReviewDto reviewDTO) {
        Review review;

        if (reviewDTO.getId() == null || reviewDTO.getId() == 0) {
            // INSERT case
            review = new Review();
            review.setCreatedAt(LocalDateTime.now());
            review.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Review> existingReview = reviewRepository.findById(reviewDTO.getId());
            if (existingReview.isEmpty()) {
                throw new RuntimeException("Review not found with ID: " + reviewDTO.getId());
            }
            review = existingReview.get();
            review.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý user relationship
        if (reviewDTO.getUserId() != null) {
            User user = userRepository.findById(reviewDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + reviewDTO.getUserId()));
            review.setUser(user);
        }

        // Xử lý doctor relationship (nếu có)
        if (reviewDTO.getDoctorId() != null) {
            DoctorProfile doctor = doctorRepository.findById(reviewDTO.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + reviewDTO.getDoctorId()));
            review.setDoctor(doctor);
        }

        // Xử lý medicine relationship (nếu có)
        if (reviewDTO.getMedicineId() != null) {
            Medicine medicine = medicineRepository.findById(reviewDTO.getMedicineId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found with ID: " + reviewDTO.getMedicineId()));
            review.setMedicine(medicine);
        }

        // Xử lý service relationship (nếu có)
        if (reviewDTO.getServiceId() != null) {
            aptech.vn.backend.entity.Service medicalService = serviceRepository.findById(reviewDTO.getServiceId())
                    .orElseThrow(() -> new RuntimeException("Service not found with ID: " + reviewDTO.getServiceId()));
            review.setService(medicalService);
        }

        // Cập nhật các trường khác
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());

        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toGetReviewDto(savedReview);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewDTO.GetReviewDto> findByUserId(Long userId) {
        return reviewRepository.findByUser_Id(userId)
                .stream()
                .map(reviewMapper::toGetReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO.GetReviewDto> findByRating(Integer rating) {
        return reviewRepository.findByRating(rating)
                .stream()
                .map(reviewMapper::toGetReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO.GetReviewDto> findByRatingGreaterThanEqual(Integer minRating) {
        return reviewRepository.findByRatingGreaterThanEqual(minRating)
                .stream()
                .map(reviewMapper::toGetReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO.GetReviewDto> findByDoctorId(Long doctorId) {
        return reviewRepository.findByDoctor_Id(doctorId)
                .stream()
                .map(reviewMapper::toGetReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO.GetReviewDto> findByMedicineId(Long medicineId) {
        return reviewRepository.findByMedicine_Id(medicineId)
                .stream()
                .map(reviewMapper::toGetReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO.GetReviewDto> findByServiceId(Long serviceId) {
        return reviewRepository.findByService_Id(serviceId)
                .stream()
                .map(reviewMapper::toGetReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO.GetReviewDto> findByDoctorIdAndMinRating(Long doctorId, Integer minRating) {
        return reviewRepository.findByDoctor_IdAndRatingGreaterThanEqual(doctorId, minRating)
                .stream()
                .map(reviewMapper::toGetReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO.GetReviewDto> findByMedicineIdAndMinRating(Long medicineId, Integer minRating) {
        return reviewRepository.findByMedicine_IdAndRatingGreaterThanEqual(medicineId, minRating)
                .stream()
                .map(reviewMapper::toGetReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO.GetReviewDto> findByServiceIdAndMinRating(Long serviceId, Integer minRating) {
        return reviewRepository.findByService_IdAndRatingGreaterThanEqual(serviceId, minRating)
                .stream()
                .map(reviewMapper::toGetReviewDto)
                .collect(Collectors.toList());
    }
}