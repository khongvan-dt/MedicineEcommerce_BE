package aptech.vn.backend.service;

import aptech.vn.backend.dto.ReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDTO> findAll();
    Optional<ReviewDTO> findById(Long id);
    ReviewDTO save(ReviewDTO reviewDTO);
    void deleteById(Long id);
    List<ReviewDTO> findByUserId(Long userId);
    List<ReviewDTO> findByRating(Integer rating);
    List<ReviewDTO> findByRatingGreaterThanEqual(Integer minRating);
    List<ReviewDTO> findByDoctorId(Long doctorId);
    List<ReviewDTO> findByMedicineId(Long medicineId);
    List<ReviewDTO> findByServiceId(Long serviceId);
    List<ReviewDTO> findByDoctorIdAndMinRating(Long doctorId, Integer minRating);
    List<ReviewDTO> findByMedicineIdAndMinRating(Long medicineId, Integer minRating);
    List<ReviewDTO> findByServiceIdAndMinRating(Long serviceId, Integer minRating);
}