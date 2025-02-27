package aptech.vn.backend.service;

import aptech.vn.backend.entity.Consultation;
import aptech.vn.backend.entity.ConsultationStatus;
import java.util.List;
import java.util.Optional;

public interface ConsultationService {
    Consultation save(Consultation consultation);
    List<Consultation> findAll();
    Optional<Consultation> findById(Long id);
    void deleteById(Long id);
    List<Consultation> findByPatientId(Long patientId);
    List<Consultation> findByDoctorId(Long doctorId);
    List<Consultation> findByStatus(ConsultationStatus status);
    List<Consultation> findByPatientIdAndStatus(Long patientId, ConsultationStatus status);
    List<Consultation> findByDoctorIdAndStatus(Long doctorId, ConsultationStatus status);
}