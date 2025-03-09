package aptech.vn.backend.service;

import aptech.vn.backend.dto.AppointmentDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AppointmentService {
    List<AppointmentDTO> findAll();
    Optional<AppointmentDTO> findById(Long id);
    AppointmentDTO save(AppointmentDTO appointmentDTO);
    void deleteById(Long id);
    List<AppointmentDTO> findByPatientId(Long patientId);
    List<AppointmentDTO> findByDoctorId(Long doctorId);
    List<AppointmentDTO> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
    List<AppointmentDTO> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
}