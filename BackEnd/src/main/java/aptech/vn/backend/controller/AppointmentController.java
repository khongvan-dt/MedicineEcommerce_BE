package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ApiResponse;
import aptech.vn.backend.entity.Appointment;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<ApiResponse>> getAllAppointments() {
        List<ApiResponse> appointments = appointmentService.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long id) {
        return appointmentService.findById(id)
                .map(appointment -> new ResponseEntity<>(appointment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAppointment(@RequestBody ApiResponse ApiResponse) {
        ApiResponse createAppointment = appointmentService.save(ApiResponse);
        return new ResponseEntity<>(createAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAppointment(
            @PathVariable Long id,
            @RequestBody ApiResponse ApiResponse) {

        return appointmentService.findById(id)
                .map(existingAppointment -> {
                    ApiResponse updatedAppointment = appointmentService.save(ApiResponse);
                    return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        return appointmentService.findById(id)
                .map(appointment -> {
                    appointmentService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ApiResponse>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        List<ApiResponse> appointments = appointmentService.findByPatientId(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ApiResponse>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        List<ApiResponse> appointments = appointmentService.findByDoctorId(doctorId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ApiResponse>> getAppointmentsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        List<ApiResponse> appointments = appointmentService.findByAppointmentDateBetween(start, end);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}/doctor/{doctorId}")
    public ResponseEntity<List<ApiResponse>> getAppointmentsByPatientAndDoctor(
            @PathVariable Long patientId,
            @PathVariable Long doctorId) {

        List<ApiResponse> appointments = appointmentService.findByPatientIdAndDoctorId(patientId, doctorId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
