package aptech.vn.backend.repository;

import aptech.vn.backend.entity.DoctorService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorServiceRepository extends JpaRepository<DoctorService, Long> {
    List<DoctorService> findByDoctor_Id(Long doctorId);
    List<DoctorService> findByService_Id(Long serviceId);
    Optional<DoctorService> findByDoctor_IdAndService_Id(Long doctorId, Long serviceId);
}