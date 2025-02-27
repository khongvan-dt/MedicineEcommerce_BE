package aptech.vn.backend.service;

import aptech.vn.backend.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review save(Review review);
    Optional<Review> findById(Long id);
    List<Review> findAll();
    Page<Review> findAll(Pageable pageable);
    void deleteById(Long id);
    List<Review> findByUserId(Long userId);
    Double getAverageRatingByUserId(Long userId);
    List<Review> findByRating(Integer rating);
}
