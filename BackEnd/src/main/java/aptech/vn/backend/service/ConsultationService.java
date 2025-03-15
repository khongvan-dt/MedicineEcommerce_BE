package aptech.vn.backend.service;

import aptech.vn.backend.dto.ConsultationDTO;
import aptech.vn.backend.entity.ConsultationStatus;
import java.util.List;
import java.util.Optional;

public interface ConsultationService {
    List<ConsultationDTO.GetDto> findAll();
    Optional<ConsultationDTO.GetDto> findById(Long id);
    ConsultationDTO.GetDto saveOrUpdate(ConsultationDTO.SaveDto consultationDTO);
    void deleteById(Long id);
    List<ConsultationDTO.GetDto> findByPatientId(Long patientId);
    List<ConsultationDTO.GetDto> findByDoctorId(Long doctorId);
    List<ConsultationDTO.GetDto> findByStatus(ConsultationStatus status);
    List<ConsultationDTO.GetDto> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<ConsultationDTO.GetDto> findByDoctorIdAndStatus(Long doctorId, ConsultationStatus status);
    List<ConsultationDTO.GetDto> findByPatientIdAndStatus(Long patientId, ConsultationStatus status);
}