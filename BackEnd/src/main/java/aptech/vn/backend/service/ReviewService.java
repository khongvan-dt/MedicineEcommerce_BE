package aptech.vn.backend.service;

import aptech.vn.backend.dto.ReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDTO.GetDto> findAll();
    Optional<ReviewDTO.GetDto> findById(Long id);
    ReviewDTO.GetDto saveOrUpdate(ReviewDTO.SaveDto reviewDTO);
    void deleteById(Long id);
    List<ReviewDTO.GetDto> findByUserId(Long userId);
    List<ReviewDTO.GetDto> findByRating(Integer rating);
    List<ReviewDTO.GetDto> findByRatingGreaterThanEqual(Integer minRating);
    List<ReviewDTO.GetDto> findByDoctorId(Long doctorId);
    List<ReviewDTO.GetDto> findByMedicineId(Long medicineId);
    List<ReviewDTO.GetDto> findByServiceId(Long serviceId);
    List<ReviewDTO.GetDto> findByDoctorIdAndMinRating(Long doctorId, Integer minRating);
    List<ReviewDTO.GetDto> findByMedicineIdAndMinRating(Long medicineId, Integer minRating);
    List<ReviewDTO.GetDto> findByServiceIdAndMinRating(Long serviceId, Integer minRating);
}