package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ReviewDTO;
import aptech.vn.backend.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO.GetReviewDto>> getAllReviews() {
        List<ReviewDTO.GetReviewDto> reviews = reviewService.findAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO.GetReviewDto> getReviewById(@PathVariable Long id) {
        return reviewService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ReviewDTO.GetReviewDto> saveOrUpdateReview(@RequestBody ReviewDTO.SaveReviewDto reviewDTO) {
        ReviewDTO.GetReviewDto savedReview = reviewService.saveOrUpdate(reviewDTO);
        return ResponseEntity.ok(savedReview);
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
    public ResponseEntity<List<ReviewDTO.GetReviewDto>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewDTO.GetReviewDto> reviews = reviewService.findByUserId(userId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-rating/{rating}")
    public ResponseEntity<List<ReviewDTO.GetReviewDto>> getReviewsByRating(@PathVariable Integer rating) {
        List<ReviewDTO.GetReviewDto> reviews = reviewService.findByRating(rating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-min-rating/{minRating}")
    public ResponseEntity<List<ReviewDTO.GetReviewDto>> getReviewsByMinRating(@PathVariable Integer minRating) {
        List<ReviewDTO.GetReviewDto> reviews = reviewService.findByRatingGreaterThanEqual(minRating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<ReviewDTO.GetReviewDto>> getReviewsByDoctorId(@PathVariable Long doctorId) {
        List<ReviewDTO.GetReviewDto> reviews = reviewService.findByDoctorId(doctorId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-medicine/{medicineId}")
    public ResponseEntity<List<ReviewDTO.GetReviewDto>> getReviewsByMedicineId(@PathVariable Long medicineId) {
        List<ReviewDTO.GetReviewDto> reviews = reviewService.findByMedicineId(medicineId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-service/{serviceId}")
    public ResponseEntity<List<ReviewDTO.GetReviewDto>> getReviewsByServiceId(@PathVariable Long serviceId) {
        List<ReviewDTO.GetReviewDto> reviews = reviewService.findByServiceId(serviceId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-doctor-and-min-rating")
    public ResponseEntity<List<ReviewDTO.GetReviewDto>> getReviewsByDoctorAndMinRating(
            @RequestParam Long doctorId, @RequestParam Integer minRating) {
        List<ReviewDTO.GetReviewDto> reviews = reviewService.findByDoctorIdAndMinRating(doctorId, minRating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-medicine-and-min-rating")
    public ResponseEntity<List<ReviewDTO.GetReviewDto>> getReviewsByMedicineAndMinRating(
            @RequestParam Long medicineId, @RequestParam Integer minRating) {
        List<ReviewDTO.GetReviewDto> reviews = reviewService.findByMedicineIdAndMinRating(medicineId, minRating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-service-and-min-rating")
    public ResponseEntity<List<ReviewDTO.GetReviewDto>> getReviewsByServiceAndMinRating(
            @RequestParam Long serviceId, @RequestParam Integer minRating) {
        List<ReviewDTO.GetReviewDto> reviews = reviewService.findByServiceIdAndMinRating(serviceId, minRating);
        return ResponseEntity.ok(reviews);
    }
}