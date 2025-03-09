package aptech.vn.backend.controller;

import aptech.vn.backend.dto.TrackingDTO;
import aptech.vn.backend.entity.TrackingStatus;
import aptech.vn.backend.service.TrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping
    public ResponseEntity<List<TrackingDTO>> getAllTrackings() {
        List<TrackingDTO> trackings = trackingService.findAll();
        return ResponseEntity.ok(trackings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackingDTO> getTrackingById(@PathVariable Long id) {
        Optional<TrackingDTO> tracking = trackingService.findById(id);
        return tracking.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TrackingDTO> createTracking(@RequestBody TrackingDTO trackingDTO) {
        TrackingDTO savedTracking = trackingService.save(trackingDTO);
        return ResponseEntity.ok(savedTracking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackingDTO> updateTracking(@PathVariable Long id, @RequestBody TrackingDTO trackingDTO) {
        if (!trackingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        TrackingDTO updatedTracking = trackingService.save(trackingDTO);
        return ResponseEntity.ok(updatedTracking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTracking(@PathVariable Long id) {
        if (!trackingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        trackingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<List<TrackingDTO>> getTrackingsByOrderId(@PathVariable Long orderId) {
        List<TrackingDTO> trackings = trackingService.findByOrderId(orderId);
        return ResponseEntity.ok(trackings);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<TrackingDTO>> getTrackingsByStatus(@PathVariable TrackingStatus status) {
        List<TrackingDTO> trackings = trackingService.findByStatus(status);
        return ResponseEntity.ok(trackings);
    }

    @GetMapping("/by-location")
    public ResponseEntity<List<TrackingDTO>> getTrackingsByLocation(@RequestParam String location) {
        List<TrackingDTO> trackings = trackingService.findByLocationContaining(location);
        return ResponseEntity.ok(trackings);
    }

    @GetMapping("/latest-by-order/{orderId}")
    public ResponseEntity<TrackingDTO> getLatestTrackingByOrderId(@PathVariable Long orderId) {
        Optional<TrackingDTO> tracking = trackingService.findLatestByOrderId(orderId);
        return tracking.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
