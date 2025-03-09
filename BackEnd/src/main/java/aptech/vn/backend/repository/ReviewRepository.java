package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser_Id(Long userId);
    List<Review> findByRating(Integer rating);
    List<Review> findByRatingGreaterThanEqual(Integer minRating);
    List<Review> findByDoctor_Id(Long doctorId);
    List<Review> findByMedicine_Id(Long medicineId);
    List<Review> findByService_Id(Long serviceId);
    List<Review> findByDoctor_IdAndRatingGreaterThanEqual(Long doctorId, Integer minRating);
    List<Review> findByMedicine_IdAndRatingGreaterThanEqual(Long medicineId, Integer minRating);
    List<Review> findByService_IdAndRatingGreaterThanEqual(Long serviceId, Integer minRating);
}