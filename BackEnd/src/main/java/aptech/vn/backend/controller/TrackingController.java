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
    public ResponseEntity<List<TrackingDTO.GetTrackingDto>> getAllTrackings() {
        List<TrackingDTO.GetTrackingDto> trackings = trackingService.findAll();
        return ResponseEntity.ok(trackings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackingDTO.GetTrackingDto> getTrackingById(@PathVariable Long id) {
        Optional<TrackingDTO.GetTrackingDto> tracking = trackingService.findById(id);
        return tracking.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TrackingDTO.GetTrackingDto> createTracking(@RequestBody TrackingDTO.SaveTrackingDto trackingDTO) {
        TrackingDTO.GetTrackingDto savedTracking = trackingService.save(trackingDTO);
        return ResponseEntity.ok(savedTracking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackingDTO.GetTrackingDto> updateTracking(
            @PathVariable Long id,
            @RequestBody TrackingDTO.SaveTrackingDto trackingDTO) {
        if (!trackingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        TrackingDTO.GetTrackingDto updatedTracking = trackingService.save(trackingDTO);
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
    public ResponseEntity<List<TrackingDTO.GetTrackingDto>> getTrackingsByOrderId(@PathVariable Long orderId) {
        List<TrackingDTO.GetTrackingDto> trackings = trackingService.findByOrderId(orderId);
        return ResponseEntity.ok(trackings);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<TrackingDTO.GetTrackingDto>> getTrackingsByStatus(@PathVariable TrackingStatus status) {
        List<TrackingDTO.GetTrackingDto> trackings = trackingService.findByStatus(status);
        return ResponseEntity.ok(trackings);
    }

    @GetMapping("/by-location")
    public ResponseEntity<List<TrackingDTO.GetTrackingDto>> getTrackingsByLocation(@RequestParam String location) {
        List<TrackingDTO.GetTrackingDto> trackings = trackingService.findByLocationContaining(location);
        return ResponseEntity.ok(trackings);
    }

    @GetMapping("/latest-by-order/{orderId}")
    public ResponseEntity<TrackingDTO.GetTrackingDto> getLatestTrackingByOrderId(@PathVariable Long orderId) {
        Optional<TrackingDTO.GetTrackingDto> tracking = trackingService.findLatestByOrderId(orderId);
        return tracking.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

