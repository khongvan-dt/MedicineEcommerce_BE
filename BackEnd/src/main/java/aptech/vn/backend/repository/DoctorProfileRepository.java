package aptech.vn.backend.repository;

import aptech.vn.backend.entity.DoctorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {
    Optional<DoctorProfile> findByUser_Id(Long userId);
    List<DoctorProfile> findBySpecializationContaining(String specialization);
    List<DoctorProfile> findByWorkplaceContaining(String workplace);
    List<DoctorProfile> findByAccountBalanceGreaterThanEqual(BigDecimal amount);
}