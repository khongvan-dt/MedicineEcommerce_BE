package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.*;
import aptech.vn.backend.entity.*;
import aptech.vn.backend.mapper.AppointmentMapper;
import aptech.vn.backend.repository.*;
import aptech.vn.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public List<ApiResponse> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ApiResponse> findById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toDto);
    }

    @Override
    public ApiResponse save(ApiResponse ApiResponse) {
        Appointment appointment = appointmentMapper.toEntity(ApiResponse);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(savedAppointment);
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<ApiResponse> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatient_Id(patientId).stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApiResponse> findByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctor_Id(doctorId).stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApiResponse> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByAppointmentDateBetween(start, end).stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApiResponse> findByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        return appointmentRepository.findByPatient_IdAndDoctor_Id(patientId, doctorId).stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }
}