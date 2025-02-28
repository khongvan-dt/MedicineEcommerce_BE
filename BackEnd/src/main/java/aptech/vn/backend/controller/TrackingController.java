package aptech.vn.backend.controller;

import aptech.vn.backend.entity.Tracking;
import aptech.vn.backend.entity.TrackingStatus;
import aptech.vn.backend.service.TrackingService;
import aptech.vn.backend.service.impl.TrackingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trackings")
public class TrackingController {

    private final TrackingService trackingService;

    @Autowired
    public TrackingController(TrackingServiceImpl trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping
    public ResponseEntity<List<Tracking>> getAllTrackings() {
        return ResponseEntity.ok(trackingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tracking> getTrackingById(@PathVariable Long id) {
        return trackingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Tracking>> getTrackingsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(trackingService.findByOrderId(orderId));
    }

    @GetMapping("/order/{orderId}/latest")
    public ResponseEntity<Tracking> getLatestTrackingByOrderId(@PathVariable Long orderId) {
        return trackingService.findLatestTrackingByOrderId(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Tracking>> getTrackingsByStatus(@PathVariable TrackingStatus status) {
        return ResponseEntity.ok(trackingService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<Tracking> createTracking(@RequestBody Tracking tracking) {
        return new ResponseEntity<>(trackingService.save(tracking), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tracking> updateTracking(@PathVariable Long id, @RequestBody Tracking tracking) {
        return trackingService.findById(id)
                .map(existingTracking -> {
                    tracking.setId(id);
                    return ResponseEntity.ok(trackingService.save(tracking));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateTrackingStatus(
            @PathVariable Long id,
            @RequestBody Map<String, TrackingStatus> statusMap) {

        TrackingStatus newStatus = statusMap.get("status");
        if (newStatus == null) {
            return ResponseEntity.badRequest().body("Status field is required");
        }

        return trackingService.findById(id)
                .map(existingTracking -> {
                    boolean updated = trackingService.updateStatus(id, newStatus);
                    if (updated) {
                        return ResponseEntity.ok("Tracking status updated successfully");
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to update tracking status");
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTracking(@PathVariable Long id) {
        return trackingService.findById(id)
                .map(tracking -> {
                    trackingService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}