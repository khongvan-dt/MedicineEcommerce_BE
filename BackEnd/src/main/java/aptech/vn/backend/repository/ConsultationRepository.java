package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Consultation;
import aptech.vn.backend.entity.ConsultationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByPatientId(Long patientId);
    List<Consultation> findByDoctorId(Long doctorId);
    List<Consultation> findByStatus(ConsultationStatus status);
    List<Consultation> findByPatientIdAndStatus(Long patientId, ConsultationStatus status);
    List<Consultation> findByDoctorIdAndStatus(Long doctorId, ConsultationStatus status);
}
