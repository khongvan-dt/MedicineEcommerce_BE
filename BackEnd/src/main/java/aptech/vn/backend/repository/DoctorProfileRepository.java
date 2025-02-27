package aptech.vn.backend.repository;

import aptech.vn.backend.entity.DoctorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {
    Optional<DoctorProfile> findByUserId(Long userId);
    List<DoctorProfile> findBySpecialization(String specialization);
    List<DoctorProfile> findByWorkplace(String workplace);
    List<DoctorProfile> findBySpecializationContaining(String specialization);
}