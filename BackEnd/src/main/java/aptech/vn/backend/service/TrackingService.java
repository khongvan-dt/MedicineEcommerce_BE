package aptech.vn.backend.service;

import aptech.vn.backend.dto.TrackingDTO;
import aptech.vn.backend.entity.TrackingStatus;

import java.util.List;
import java.util.Optional;

public interface TrackingService {
    List<TrackingDTO.GetTrackingDto> findAll();
    Optional<TrackingDTO.GetTrackingDto> findById(Long id);
    TrackingDTO.GetTrackingDto save(TrackingDTO.SaveTrackingDto trackingDTO);
    void deleteById(Long id);
    List<TrackingDTO.GetTrackingDto> findByOrderId(Long orderId);
    List<TrackingDTO.GetTrackingDto> findByStatus(TrackingStatus status);
    List<TrackingDTO.GetTrackingDto> findByLocationContaining(String location);
    Optional<TrackingDTO.GetTrackingDto> findLatestByOrderId(Long orderId);
}