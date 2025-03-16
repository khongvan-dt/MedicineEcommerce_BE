package aptech.vn.backend.service;

import aptech.vn.backend.dto.ConsultationDTO;
import aptech.vn.backend.entity.ConsultationStatus;
import java.util.List;
import java.util.Optional;

public interface ConsultationService {
    List<ConsultationDTO.GetConsultationDto> findAll();
    Optional<ConsultationDTO.GetConsultationDto> findById(Long id);
    ConsultationDTO.GetConsultationDto saveOrUpdate(ConsultationDTO.SaveConsultationDto consultationDTO);
    void deleteById(Long id);
    List<ConsultationDTO.GetConsultationDto> findByPatientId(Long patientId);
    List<ConsultationDTO.GetConsultationDto> findByDoctorId(Long doctorId);
    List<ConsultationDTO.GetConsultationDto> findByStatus(ConsultationStatus status);
    List<ConsultationDTO.GetConsultationDto> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<ConsultationDTO.GetConsultationDto> findByDoctorIdAndStatus(Long doctorId, ConsultationStatus status);
    List<ConsultationDTO.GetConsultationDto> findByPatientIdAndStatus(Long patientId, ConsultationStatus status);
}