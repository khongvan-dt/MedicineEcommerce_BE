package aptech.vn.backend.service;

import aptech.vn.backend.dto.ReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDTO.GetReviewDto> findAll();
    Optional<ReviewDTO.GetReviewDto> findById(Long id);
    ReviewDTO.GetReviewDto saveOrUpdate(ReviewDTO.SaveReviewDto reviewDTO);
    void deleteById(Long id);
    List<ReviewDTO.GetReviewDto> findByUserId(Long userId);
    List<ReviewDTO.GetReviewDto> findByRating(Integer rating);
    List<ReviewDTO.GetReviewDto> findByRatingGreaterThanEqual(Integer minRating);
    List<ReviewDTO.GetReviewDto> findByDoctorId(Long doctorId);
    List<ReviewDTO.GetReviewDto> findByMedicineId(Long medicineId);
    List<ReviewDTO.GetReviewDto> findByServiceId(Long serviceId);
    List<ReviewDTO.GetReviewDto> findByDoctorIdAndMinRating(Long doctorId, Integer minRating);
    List<ReviewDTO.GetReviewDto> findByMedicineIdAndMinRating(Long medicineId, Integer minRating);
    List<ReviewDTO.GetReviewDto> findByServiceIdAndMinRating(Long serviceId, Integer minRating);
}