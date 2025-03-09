package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.TrackingDTO;
import aptech.vn.backend.entity.Tracking;
import aptech.vn.backend.entity.TrackingStatus;
import aptech.vn.backend.mapper.TrackingMapper;
import aptech.vn.backend.repository.TrackingRepository;
import aptech.vn.backend.service.TrackingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackingServiceImpl implements TrackingService {
    private final TrackingMapper trackingMapper;
    private final TrackingRepository trackingRepository;

    public TrackingServiceImpl(TrackingRepository trackingRepository, TrackingMapper trackingMapper) {
        this.trackingRepository = trackingRepository;
        this.trackingMapper = trackingMapper;
    }

    @Override
    public List<TrackingDTO> findAll() {
        return trackingRepository.findAll()
                .stream()
                .map(trackingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TrackingDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public TrackingDTO save(TrackingDTO trackingDTO) {
        Tracking tracking = trackingMapper.toEntity(trackingDTO);
        trackingRepository.save(tracking);
        return trackingMapper.toDto(tracking);
    }

    @Override
    public void deleteById(Long id) {
        trackingRepository.deleteById(id);
    }

    @Override
    public List<TrackingDTO> findByOrderId(Long orderId) {
        return trackingRepository.findByOrder_Id(orderId)
                .stream()
                .map(trackingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackingDTO> findByStatus(TrackingStatus status) {
        return trackingRepository.findByStatus(status)
                .stream()
                .map(trackingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackingDTO> findByLocationContaining(String location) {
        return trackingRepository.findByLocationContaining(location)
                .stream()
                .map(trackingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TrackingDTO> findLatestByOrderId(Long orderId) {
        return  trackingRepository.findFirstByOrder_IdOrderByCreatedAtDesc(orderId).map(trackingMapper::toDto);
    }
}
