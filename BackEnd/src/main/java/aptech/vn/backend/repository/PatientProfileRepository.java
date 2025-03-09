package aptech.vn.backend.repository;

import aptech.vn.backend.entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {
    Optional<PatientProfile> findByUser_Id(Long userId);
    List<PatientProfile> findByBloodType(String bloodType);
    List<PatientProfile> findByMedicalHistoryContaining(String keyword);
    List<PatientProfile> findByAllergiesContaining(String keyword);
    List<PatientProfile> findByAccountBalanceGreaterThanEqual(BigDecimal amount);
}