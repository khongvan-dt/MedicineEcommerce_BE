package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);
    List<Review> findByRating(Integer rating);
    List<Review> findByRatingGreaterThanEqual(Integer minRating);
    List<Review> findByCommentContaining(String keyword);
}
