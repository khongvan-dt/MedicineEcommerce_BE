package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Consultation;
import aptech.vn.backend.entity.ConsultationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByPatient_Id(Long patientId);
    List<Consultation> findByDoctor_Id(Long doctorId);
    List<Consultation> findByStatus(ConsultationStatus status);
    List<Consultation> findByPatient_IdAndDoctor_Id(Long patientId, Long doctorId);
    List<Consultation> findByDoctor_IdAndStatus(Long doctorId, ConsultationStatus status);
    List<Consultation> findByPatient_IdAndStatus(Long patientId, ConsultationStatus status);
}