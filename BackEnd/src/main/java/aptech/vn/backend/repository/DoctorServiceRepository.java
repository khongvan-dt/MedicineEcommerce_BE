package aptech.vn.backend.repository;

import aptech.vn.backend.entity.DoctorService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorServiceRepository extends JpaRepository<DoctorService, Long> {
    List<DoctorService> findByDoctorId(Long doctorId);
    List<DoctorService> findByServiceId(Long serviceId);
    Optional<DoctorService> findByDoctorIdAndServiceId(Long doctorId, Long serviceId);
}