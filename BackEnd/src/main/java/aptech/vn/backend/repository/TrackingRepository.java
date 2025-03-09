package aptech.vn.backend.repository;

import aptech.vn.backend.entity.TrackingStatus;
import aptech.vn.backend.entity.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    List<Tracking> findByOrder_Id(Long orderId);
    List<Tracking> findByStatus(TrackingStatus status);
    List<Tracking> findByLocationContaining(String location);
    Optional<Tracking> findFirstByOrder_IdOrderByCreatedAtDesc(Long orderId);
}