package aptech.vn.backend.service;

import aptech.vn.backend.dto.TrackingDTO;
import aptech.vn.backend.entity.TrackingStatus;

import java.util.List;
import java.util.Optional;

public interface TrackingService {
    List<TrackingDTO> findAll();
    Optional<TrackingDTO> findById(Long id);
    TrackingDTO save(TrackingDTO trackingDTO);
    void deleteById(Long id);
    List<TrackingDTO> findByOrderId(Long orderId);
    List<TrackingDTO> findByStatus(TrackingStatus status);
    List<TrackingDTO> findByLocationContaining(String location);
    Optional<TrackingDTO> findLatestByOrderId(Long orderId);
}