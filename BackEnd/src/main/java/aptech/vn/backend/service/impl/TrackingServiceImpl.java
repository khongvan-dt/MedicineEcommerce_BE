package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.Tracking;
import aptech.vn.backend.entity.TrackingStatus;
import aptech.vn.backend.repository.TrackingRepository;
import aptech.vn.backend.service.TrackingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class TrackingServiceImpl implements TrackingService {

    private final TrackingRepository trackingRepository;

    public TrackingServiceImpl(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    @Override
    public Tracking save(Tracking tracking) {
        return trackingRepository.save(tracking);
    }

    @Override
    public Optional<Tracking> findById(Long id) {
        return trackingRepository.findById(id);
    }

    @Override
    public List<Tracking> findAll() {
        return trackingRepository.findAll();
    }

    @Override
    public Page<Tracking> findAll(Pageable pageable) {
        return trackingRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        trackingRepository.deleteById(id);
    }

    @Override
    public List<Tracking> findByOrderId(Long orderId) {
        return trackingRepository.findByOrderId(orderId);
    }

    @Override
    public List<Tracking> findByStatus(TrackingStatus status) {
        return trackingRepository.findByStatus(status);
    }

    @Override
    public boolean updateStatus(Long trackingId, TrackingStatus newStatus) {
        return false;
    }

    @Override
    public Optional<Tracking> findLatestTrackingByOrderId(Long orderId) {
        return Optional.empty();
    }
}
