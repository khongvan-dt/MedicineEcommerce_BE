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
    public ResponseEntity<List<ReviewDTO.GetDto>> getAllReviews() {
        List<ReviewDTO.GetDto> reviews = reviewService.findAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO.GetDto> getReviewById(@PathVariable Long id) {
        return reviewService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ReviewDTO.GetDto> saveOrUpdateReview(@RequestBody ReviewDTO.SaveDto reviewDTO) {
        ReviewDTO.GetDto savedReview = reviewService.saveOrUpdate(reviewDTO);
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
    public ResponseEntity<List<ReviewDTO.GetDto>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewDTO.GetDto> reviews = reviewService.findByUserId(userId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-rating/{rating}")
    public ResponseEntity<List<ReviewDTO.GetDto>> getReviewsByRating(@PathVariable Integer rating) {
        List<ReviewDTO.GetDto> reviews = reviewService.findByRating(rating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-min-rating/{minRating}")
    public ResponseEntity<List<ReviewDTO.GetDto>> getReviewsByMinRating(@PathVariable Integer minRating) {
        List<ReviewDTO.GetDto> reviews = reviewService.findByRatingGreaterThanEqual(minRating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<ReviewDTO.GetDto>> getReviewsByDoctorId(@PathVariable Long doctorId) {
        List<ReviewDTO.GetDto> reviews = reviewService.findByDoctorId(doctorId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-medicine/{medicineId}")
    public ResponseEntity<List<ReviewDTO.GetDto>> getReviewsByMedicineId(@PathVariable Long medicineId) {
        List<ReviewDTO.GetDto> reviews = reviewService.findByMedicineId(medicineId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-service/{serviceId}")
    public ResponseEntity<List<ReviewDTO.GetDto>> getReviewsByServiceId(@PathVariable Long serviceId) {
        List<ReviewDTO.GetDto> reviews = reviewService.findByServiceId(serviceId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-doctor-and-min-rating")
    public ResponseEntity<List<ReviewDTO.GetDto>> getReviewsByDoctorAndMinRating(
            @RequestParam Long doctorId, @RequestParam Integer minRating) {
        List<ReviewDTO.GetDto> reviews = reviewService.findByDoctorIdAndMinRating(doctorId, minRating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-medicine-and-min-rating")
    public ResponseEntity<List<ReviewDTO.GetDto>> getReviewsByMedicineAndMinRating(
            @RequestParam Long medicineId, @RequestParam Integer minRating) {
        List<ReviewDTO.GetDto> reviews = reviewService.findByMedicineIdAndMinRating(medicineId, minRating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-service-and-min-rating")
    public ResponseEntity<List<ReviewDTO.GetDto>> getReviewsByServiceAndMinRating(
            @RequestParam Long serviceId, @RequestParam Integer minRating) {
        List<ReviewDTO.GetDto> reviews = reviewService.findByServiceIdAndMinRating(serviceId, minRating);
        return ResponseEntity.ok(reviews);
    }
}