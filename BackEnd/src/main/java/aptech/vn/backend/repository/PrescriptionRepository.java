package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByDoctorId(Long doctorId);
    List<Prescription> findByPatientId(Long patientId);
    List<Prescription> findByMedicineId(Long medicineId);
    List<Prescription> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<Prescription> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}