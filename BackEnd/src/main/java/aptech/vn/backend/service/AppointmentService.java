package aptech.vn.backend.service;

import aptech.vn.backend.dto.ApiResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AppointmentService {
    List<ApiResponse> findAll();
    Optional<ApiResponse> findById(Long id);
    ApiResponse save(ApiResponse ApiResponse);
    void deleteById(Long id);
    List<ApiResponse> findByPatientId(Long patientId);
    List<ApiResponse> findByDoctorId(Long doctorId);
    List<ApiResponse> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
    List<ApiResponse> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
}