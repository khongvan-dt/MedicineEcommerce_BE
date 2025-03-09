package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByDoctor_Id(Long doctorId);
    List<Prescription> findByPatient_Id(Long patientId);
    List<Prescription> findByMedicine_Id(Long medicineId);
    List<Prescription> findByPatient_IdAndDoctor_Id(Long patientId, Long doctorId);
    List<Prescription> findByPatient_IdAndMedicine_Id(Long patientId, Long medicineId);
}