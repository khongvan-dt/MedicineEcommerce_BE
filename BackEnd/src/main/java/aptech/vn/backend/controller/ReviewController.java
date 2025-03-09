package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ReviewDTO;
import aptech.vn.backend.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.findAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        Optional<ReviewDTO> review = reviewService.findById(id);
        return review.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO savedReview = reviewService.save(reviewDTO);
        return ResponseEntity.ok(savedReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        if (!reviewService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ReviewDTO updatedReview = reviewService.save(reviewDTO);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (!reviewService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewDTO> reviews = reviewService.findByUserId(userId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-rating/{rating}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByRating(@PathVariable Integer rating) {
        List<ReviewDTO> reviews = reviewService.findByRating(rating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-min-rating/{minRating}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByMinRating(@PathVariable Integer minRating) {
        List<ReviewDTO> reviews = reviewService.findByRatingGreaterThanEqual(minRating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByDoctorId(@PathVariable Long doctorId) {
        List<ReviewDTO> reviews = reviewService.findByDoctorId(doctorId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-medicine/{medicineId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByMedicineId(@PathVariable Long medicineId) {
        List<ReviewDTO> reviews = reviewService.findByMedicineId(medicineId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-service/{serviceId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByServiceId(@PathVariable Long serviceId) {
        List<ReviewDTO> reviews = reviewService.findByServiceId(serviceId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-doctor-and-min-rating")
    public ResponseEntity<List<ReviewDTO>> getReviewsByDoctorAndMinRating(
            @RequestParam Long doctorId, @RequestParam Integer minRating) {
        List<ReviewDTO> reviews = reviewService.findByDoctorIdAndMinRating(doctorId, minRating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-medicine-and-min-rating")
    public ResponseEntity<List<ReviewDTO>> getReviewsByMedicineAndMinRating(
            @RequestParam Long medicineId, @RequestParam Integer minRating) {
        List<ReviewDTO> reviews = reviewService.findByMedicineIdAndMinRating(medicineId, minRating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-service-and-min-rating")
    public ResponseEntity<List<ReviewDTO>> getReviewsByServiceAndMinRating(
            @RequestParam Long serviceId, @RequestParam Integer minRating) {
        List<ReviewDTO> reviews = reviewService.findByServiceIdAndMinRating(serviceId, minRating);
        return ResponseEntity.ok(reviews);
    }
}
