package aptech.vn.backend.service;

import aptech.vn.backend.dto.ConsultationDTO;
import aptech.vn.backend.entity.ConsultationStatus;
import java.util.List;
import java.util.Optional;

public interface ConsultationService {
    List<ConsultationDTO> findAll();
    Optional<ConsultationDTO> findById(Long id);
    ConsultationDTO save(ConsultationDTO consultationDTO);
    void deleteById(Long id);
    List<ConsultationDTO> findByPatientId(Long patientId);
    List<ConsultationDTO> findByDoctorId(Long doctorId);
    List<ConsultationDTO> findByStatus(ConsultationStatus status);
    List<ConsultationDTO> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<ConsultationDTO> findByDoctorIdAndStatus(Long doctorId, ConsultationStatus status);
    List<ConsultationDTO> findByPatientIdAndStatus(Long patientId, ConsultationStatus status);
}