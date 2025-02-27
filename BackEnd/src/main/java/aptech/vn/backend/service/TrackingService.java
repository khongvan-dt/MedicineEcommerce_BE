package aptech.vn.backend.service;

import aptech.vn.backend.entity.Tracking;
import aptech.vn.backend.entity.TrackingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TrackingService {
    Tracking save(Tracking tracking);
    Optional<Tracking> findById(Long id);
    List<Tracking> findAll();
    Page<Tracking> findAll(Pageable pageable);
    void deleteById(Long id);
    List<Tracking> findByOrderId(Long orderId);
    List<Tracking> findByStatus(TrackingStatus status);
    boolean updateStatus(Long trackingId, TrackingStatus newStatus);
    Optional<Tracking> findLatestTrackingByOrderId(Long orderId);
}