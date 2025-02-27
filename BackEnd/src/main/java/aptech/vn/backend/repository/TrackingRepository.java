package aptech.vn.backend.repository;

import aptech.vn.backend.entity.TrackingStatus;
import aptech.vn.backend.entity.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    List<Tracking> findByOrderId(Long orderId);
    List<Tracking> findByStatus(TrackingStatus status);
    List<Tracking> findByOrderIdAndStatus(Long orderId, TrackingStatus status);
    List<Tracking> findByLocationContaining(String keyword);
}