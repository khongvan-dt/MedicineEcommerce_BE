package aptech.vn.backend.service;

import aptech.vn.backend.entity.Appointment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment save(Appointment appointment);
    List<Appointment> findAll();
    Optional<Appointment> findById(Long id);
    void deleteById(Long id);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
    List<Appointment> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
}